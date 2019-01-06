/**
 *
 */
package com.shalahuddin.web.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shalahuddin.web.model.Menu;
import com.shalahuddin.web.model.Role;
import com.shalahuddin.web.repository.MenuRepository;
import com.shalahuddin.web.repository.RoleRepository;

@Service("menuService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MenuService {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public Menu saveMenu(Menu menu){
		return menuRepository.save(menu);
	}

	@Transactional
	public Role saveRole(Role role){
		return roleRepository.save(role);
	}

	public List<Menu> findMenuTree() {
		List<Menu> result = new ArrayList<>();
		List<Menu> rootMenuList = menuRepository.findByActiveAndParentIdOrderByPosition(true, null);
		logger.debug("root menu={}", rootMenuList);
		for (Menu root : rootMenuList) {
			root.setChild(findChild(root.getId(), 1));
			result.add(root);
		}
		logger.info("result={}",result);
		return result;
	}

	private List<Menu> findChild(String parentId, int level) {
		logger.debug("findChild parentId={}, level={}", new Object[]{parentId, level});
		List<Menu> result = new ArrayList<>();
		List<Menu> listData = menuRepository.findByActiveAndParentIdOrderByPosition(true, parentId);
		for (Menu menu : listData) {
			menu.setLevel(level);
			result.add(menu);
			menu.setChild(findChild(menu.getId(), level+1));
		}
		return result;
	}
}
