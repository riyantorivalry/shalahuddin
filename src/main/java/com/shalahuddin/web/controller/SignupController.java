package com.shalahuddin.web.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shalahuddin.web.common.CrudController;
import com.shalahuddin.web.form.SignupForm;
import com.shalahuddin.web.model.Account;
import com.shalahuddin.web.model.Role;
import com.shalahuddin.web.repository.AccountRepository;
import com.shalahuddin.web.repository.RoleRepository;
import com.shalahuddin.web.service.AccountService;
import com.shalahuddin.web.support.web.Ajax;
import com.shalahuddin.web.support.web.MessageHelper;

@Controller
class SignupController extends CrudController<SignupForm, Account>{
	Logger logger = LoggerFactory.getLogger(getClass());

	private static final String SIGNUP_VIEW_NAME = "signup/signup";

	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private RoleRepository roleRepo;

	@ModelAttribute("roleList")
	public List<Role> populateRoles(){
		try{
			return roleRepo.findAll();
		}catch(Exception e){
			logger.error("failed populate role", e);
		}
		return Collections.emptyList();
	}

	@GetMapping("signup")
	String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(new SignupForm());
		if (Ajax.isAjaxRequest(requestedWith)) {
			return SIGNUP_VIEW_NAME.concat(" :: signupForm");
		}
		return SIGNUP_VIEW_NAME;
	}

	@PostMapping("signup")
	String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		Account account = accountService.saveAccount(toEntity(signupForm));
		accountService.signin(account);
		// see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
		MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}

	@Override
	protected Account toEntity(SignupForm form, boolean isEdit) {
		Account user = new Account();
		if (isEdit && StringUtils.isNotBlank(form.getUserId())) {
			user = accountRepo.findOne(form.getUserId());
		}
		BeanUtils.copyProperties(form, user);
		if(StringUtils.isNotBlank(form.getRole())){
			user.setRole(roleRepo.findOne(form.getRole()));
		}
		return user;
	}

	@Override
	protected SignupForm toForm(Account entity) {
		SignupForm userForm = new SignupForm();
		if(entity != null){
			BeanUtils.copyProperties(entity, userForm);
			if(entity.getRole() != null){
				userForm.setRole(entity.getRole().getId());
			}
		}
		return userForm;
	}
}
