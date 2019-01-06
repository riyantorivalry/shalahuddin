/**
 *
 */
package com.shalahuddin.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shalahuddin.web.common.CrudController;
import com.shalahuddin.web.form.MenuForm;
import com.shalahuddin.web.form.RoleForm;
import com.shalahuddin.web.model.Menu;
import com.shalahuddin.web.model.Role;
import com.shalahuddin.web.repository.MenuRepository;
import com.shalahuddin.web.repository.RoleRepository;
import com.shalahuddin.web.service.AccountService;
import com.shalahuddin.web.service.MenuService;
import com.shalahuddin.web.support.web.Ajax;
import com.shalahuddin.web.support.web.MessageHelper;

@Controller
@RequestMapping(value="role")
public class RoleController extends CrudController<RoleForm, Role> {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	private static final String LIST_VIEW_NAME = "role/list";
	private static final String FORM_VIEW_NAME = "role/form";
	private static final String DUPLICATE_MESSAGE = "duplicate.message";

	private static final String ROLE_MENU_LIST_VIEW_NAME = "role/menu";

	private static final String ROLE_MENU_ATTRIBUTE_NAME = "roleMenuFormList";
	private static final String ROLE_FORM_ATTRIBUTE_NAME = "roleForm";

	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private MenuRepository menuRepo;
	@Autowired
	private AccountService accountService;
	@Autowired
	private MenuService menuService;

	@ModelAttribute("module")
	String module() {
		return "usermgmt";
	}

	@GetMapping({"","list"})
	public String list(Model model){
		List<Role> list = roleRepo.findAll();
		logger.info("list={}", list);
		List<RoleForm> listForm = toListForm(list);
		if(listForm != null && listForm.size() > 0){
			model.addAttribute("roleList", listForm);
		}
		return LIST_VIEW_NAME;
	}

	@GetMapping({ "add", "edit" })
	public String form(Model model, String roleId, RedirectAttributes ra,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
		logger.debug("roleId={}", roleId);
		if (StringUtils.isNotBlank(roleId)) {
			Role role = roleRepo.findOne(roleId);
			logger.debug("edit role={}", role);
			model.addAttribute("form_edit", "1");
			model.addAttribute(toForm(role));
		} else {
			model.addAttribute("form_edit", "0");
			model.addAttribute(new RoleForm());
		}
		if (Ajax.isAjaxRequest(requestedWith)) {
			logger.debug("ajax request");
			return FORM_VIEW_NAME.concat(" :: form");
		}
		return FORM_VIEW_NAME;
	}

	@PostMapping("save")
	public String save(@Valid @ModelAttribute RoleForm roleForm, Errors errors, RedirectAttributes ra,
			@ModelAttribute("form_edit") String form_edit) {
		logger.debug("save role={}", roleForm);
		if (errors.hasErrors()) {
			return FORM_VIEW_NAME;
		}
		boolean isEdit = form_edit.equalsIgnoreCase("1");
		logger.debug("form_edit={}", form_edit);
		// validate unique constraint
		if (!isEdit && roleRepo.exists(roleForm.getId()) ) {
			errors.rejectValue("id", DUPLICATE_MESSAGE, new Object[] { "Role Code" }, "already exists");
			return FORM_VIEW_NAME;
		}
		try{
			Role role = toEntity(roleForm, isEdit);
			role = accountService.saveRole(role);

			logger.info("saved entity={}", role);
			MessageHelper.addSuccessAttribute(ra, "role.save.success");
		}catch(Exception e){
			logger.error("failed save user", e);
			MessageHelper.addErrorAttribute(ra, "role.save.error");
		}
		return "redirect:/role";

	}

	@GetMapping("delete")
	public String delete(String roleId, RedirectAttributes ra) {
		logger.info("delete {}", roleId);

		try {
			roleRepo.delete(roleId);
			MessageHelper.addSuccessAttribute(ra, "role.delete.success");
		} catch (Exception e) {
			logger.error("failed delete User ", e);
			MessageHelper.addErrorAttribute(ra, "role.delete.error");
		}
		return "redirect:/role";
	}

	@GetMapping("menus")
	public String menu(Model model, @RequestParam(value = "roleId", required = true) String roleId,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
		logger.debug("roleCode={}", roleId);
		List<MenuForm> menuFormList = new ArrayList<>();

		Role role = roleRepo.findOne(roleId);
		RoleForm roleForm = toForm(role);
		model.addAttribute(ROLE_FORM_ATTRIBUTE_NAME, roleForm);

		List<Menu> menuList = menuService.findMenuTree();
		logger.debug("list active menu={}", menuList);

		Set<Menu> menuSet = role.getMenus();
		List<String> checkedMenuCodeList = new ArrayList<>();

		for (Menu menu : menuSet) {
			checkedMenuCodeList.add(menu.getId());
		}
		if(menuList != null){
			for (Menu menu : menuList) {
				menuFormList.add(toRoleMenuForm(menu, checkedMenuCodeList));
			}
		}

		// all role-menu
		model.addAttribute(ROLE_MENU_ATTRIBUTE_NAME, menuFormList);
		if (Ajax.isAjaxRequest(requestedWith)) {
			logger.debug("ajax request");
			return ROLE_MENU_LIST_VIEW_NAME.concat(" :: form");
		}
		return ROLE_MENU_LIST_VIEW_NAME;
	}

	@PostMapping(value = "saveMenus")
	public String saveMenus(@ModelAttribute("roleForm") RoleForm roleForm, Errors errors, RedirectAttributes ra) {
		logger.info("saveMenus Data {}", roleForm);

		Role role = roleRepo.findOne(roleForm.getId());

		String[] menuIds = roleForm.getMenus();
		role.getMenus().clear();
		if(menuIds != null && menuIds.length > 0){
			for (String menuId : menuIds) {
				Menu menu = menuRepo.findOne(menuId);
				role.addMenu(menu);
			}
		}

		try{
			role = accountService.saveRole(role);
			logger.debug("saved role={}", role);
			MessageHelper.addSuccessAttribute(ra, "role.menu.form.save.success");
		}catch(Exception e){
			logger.error("failed save role", e);
			MessageHelper.addErrorAttribute(ra, "role.menu.form.save.error");
		}
		return "redirect:/role";
	}

	@Override
	protected Role toEntity(RoleForm form, boolean isEdit) {
		Role role = new Role();
		if (isEdit && StringUtils.isNotBlank(form.getId())) {
			role = roleRepo.findOne(form.getId());
		}
		BeanUtils.copyProperties(form, role);
		return role;
	}

	@Override
	protected RoleForm toForm(Role entity) {
		if(entity==null){
			return null;
		}
		RoleForm form = new RoleForm();
		if (entity != null) {
			BeanUtils.copyProperties(entity, form, "menus");

			Set<Menu> menuSet = entity.getMenus();
			if(menuSet != null && !menuSet.isEmpty()){
				String[] menus = new String[menuSet.size()];
				int i=0;
				for(Menu menu : menuSet){
					menus[i] = menu.getId();
					i++;
				}
				form.setMenus(menus);
			}
		}
		return form;
	}

	private MenuForm toRoleMenuForm(Menu menu, List<String> checkedMenuCodeList) {
		MenuForm form = new MenuForm();
		BeanUtils.copyProperties(menu, form);

		if(checkedMenuCodeList.contains(menu.getId())){
			form.setChecked(true);
		} else {
			form.setChecked(false);
		}

		if (menu.getChild() != null && !menu.getChild().isEmpty()) {
			List<MenuForm> list = new ArrayList<>();
			for(Menu child: menu.getChild()){
				list.add(toRoleMenuForm(child, checkedMenuCodeList));
			}
			form.setChild(list);
		}

		logger.info("RoleMenuForm={}, checkedMenuCodeList={}", form, checkedMenuCodeList);

		return form;
	}

}
