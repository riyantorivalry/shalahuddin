package com.shalahuddin.web.advice;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shalahuddin.web.model.Menu;
import com.shalahuddin.web.model.Role;
import com.shalahuddin.web.repository.RoleRepository;

//@ControllerAdvice
@Deprecated /** move menu authorities and last login date to session (LoginListener) */
public class RoleAdvice {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RoleRepository roleRepository;

	@ModelAttribute("menuAuthorities")
	public Set<String> populateMenuByRole() {
		Set<String> menuAuthorities = new HashSet<>();

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			logger.debug("authentication={}", SecurityContextHolder.getContext().getAuthentication());
			for (GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
				Role role = roleRepository.findOneByIdAndActive(auth.getAuthority(), true);
				if(role != null){
					Set<Menu> menuSet = role.getMenus();
					for (Menu menu : menuSet) {
						if(menu.isActive()){
							menuAuthorities.add(menu.getId());
						}
					}
				}
			}
		} else {
			logger.debug("not logged in yet");
		}
		return menuAuthorities;
	}
}
