/**
 *
 */
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
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(value="account")
public class AccountController extends CrudController<SignupForm, Account>{
	Logger logger = LoggerFactory.getLogger(getClass());
	private static final String ACCOUNT_LIST_VIEW_NAME = "account/list";
	private static final String ACCOUNT_FORM_VIEW_NAME = "account/form";
	private static final String DUPLICATE_MESSAGE = "duplicate.message";

	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleRepository roleRepo;

	@ModelAttribute("module")
	String module() {
		return "usermgmt";
	}

	@ModelAttribute("roleList")
	public List<Role> populateRoles(){
		try{
			return roleRepo.findByActive(true);
		}catch(Exception e){
			logger.error("failed populate role", e);
		}
		return Collections.emptyList();
	}

	@GetMapping({"","list"})
	public String list(Model model){
		List<Account> list = accountRepo.findAll();
		logger.info("list={}", list);
		List<SignupForm> listForm = toListForm(list);
		if(listForm != null && listForm.size() > 0){
			model.addAttribute("accountList", listForm);
		}
		return ACCOUNT_LIST_VIEW_NAME;
	}

	@GetMapping({ "add", "edit" })
	public String form(Model model, String userId, RedirectAttributes ra,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
		logger.debug("userId={}", userId);
		if (StringUtils.isNotBlank(userId)) {
			Account account = accountRepo.findOne(userId);
			logger.debug("edit user={}", account);
			model.addAttribute("form_edit", "1");
			model.addAttribute(toForm(account));
		} else {
			model.addAttribute("form_edit", "0");
			model.addAttribute(new SignupForm());
		}
		if (Ajax.isAjaxRequest(requestedWith)) {
			logger.debug("ajax request");
			return ACCOUNT_FORM_VIEW_NAME.concat(" :: signupForm");
		}
		return ACCOUNT_FORM_VIEW_NAME;
	}

	@PostMapping("save")
	public String save(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra,
			@ModelAttribute("form_edit") String form_edit) {
		logger.debug("save account={}", signupForm);
		if (errors.hasErrors()) {
			return ACCOUNT_FORM_VIEW_NAME;
		}
		boolean isEdit = form_edit.equalsIgnoreCase("1");
		logger.debug("form_edit={}", form_edit);
		// validate unique constraint
		if (!isEdit && accountRepo.exists(signupForm.getUserId()) ) {
			errors.rejectValue("userId", DUPLICATE_MESSAGE, new Object[] { "UserId" }, "already exists");
			return ACCOUNT_FORM_VIEW_NAME;
		}
		if (!isEdit && accountRepo.existsEmail(signupForm.getEmail()).get() > 0 ) {
			errors.rejectValue("email", DUPLICATE_MESSAGE, new Object[] { "Email" }, "already exists");
			return ACCOUNT_FORM_VIEW_NAME;
		}
		try{
			Account account = toEntity(signupForm, isEdit);
			if(isEdit){
				accountService.saveAccount(account, false);
			} else {
				accountService.saveAccount(account);
			}

			logger.info("saved entity={}", account);
			MessageHelper.addSuccessAttribute(ra, "account.save.success");
		}catch(Exception e){
			logger.error("failed save user", e);
			MessageHelper.addErrorAttribute(ra, "account.save.error");
		}
		return "redirect:/account";

	}

	@GetMapping("delete")
	public String delete(String userId, RedirectAttributes ra) {
		logger.info("delete {}", userId);

		try {
			accountRepo.delete(userId);
			MessageHelper.addSuccessAttribute(ra, "account.delete.success");
		} catch (Exception e) {
			logger.error("failed delete User ", e);
			MessageHelper.addErrorAttribute(ra, "account.delete.error");
		}
		return "redirect:/account";
	}

	@Override
	protected Account toEntity(SignupForm form, boolean isEdit) {
		Account account = new Account();
		if (isEdit && StringUtils.isNotBlank(form.getUserId())) {
			account = accountRepo.findOne(form.getUserId());
		}
		BeanUtils.copyProperties(form, account);
		if(StringUtils.isNotBlank(form.getRole())){
			account.setRole(roleRepo.findOne(form.getRole()));
		}
		return account;
	}

	@Override
	protected SignupForm toForm(Account entity) {
		SignupForm accountForm = new SignupForm();
		if(entity != null){
			BeanUtils.copyProperties(entity, accountForm);
			if(entity.getRole() != null){
				accountForm.setRole(entity.getRole().getId());
			}
		}
		return accountForm;
	}
}
