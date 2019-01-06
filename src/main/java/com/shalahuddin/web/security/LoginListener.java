/**
 *
 */
package com.shalahuddin.web.security;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.shalahuddin.web.model.Account;
import com.shalahuddin.web.model.ActiveSession;
import com.shalahuddin.web.model.Menu;
import com.shalahuddin.web.model.Role;
import com.shalahuddin.web.repository.AccountRepository;
import com.shalahuddin.web.service.AccountService;
import com.shalahuddin.web.service.DateTimeService;
import com.shalahuddin.web.util.IConstants;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
	private static final Logger logger = LoggerFactory.getLogger(LoginListener.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private DateTimeService dateTimeService;
	@Autowired
	private HttpSession httpSession;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		logger.info("successfully authenticated");
		String userName = null;
		String remoteAddress = null;
		Set<String> menuAuthorities = new HashSet<>();
		Object principal = event.getAuthentication().getPrincipal();
		Object details = event.getAuthentication().getDetails();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		if (details instanceof WebAuthenticationDetails){
			remoteAddress = ((WebAuthenticationDetails)details).getRemoteAddress();
		}
		logger.debug("http session={}", httpSession.getId());
		Account accountLogin = accountRepository.findOne(userName);
		logger.debug("account login={}", accountLogin);
		if(accountLogin != null){
			if(accountLogin.getLastLogin() != null){
				httpSession.setAttribute(IConstants.LAST_LOGIN_DATE, Date.from(accountLogin.getLastLogin()));
			}
			// put Role user login to session
			httpSession.setAttribute(IConstants.SESSION_USER_ROLE, accountLogin.getRole() != null? accountLogin.getRole().getId():null);

			// update last login
			accountService.updateLastLogin(userName);
			Role role = accountLogin.getRole();
			if(role != null && role.isActive()){
				Set<Menu> menuSet = role.getMenus();
				for (Menu menu : menuSet) {
					if(menu.isActive()){
						menuAuthorities.add(menu.getId());
					}
				}
				logger.debug("add menus to session: {}", menuAuthorities);
				httpSession.setAttribute(IConstants.MENU_AUTHORITIES, menuAuthorities);
			}
		}

		ActiveSession session = new ActiveSession();
		session.setRemoteAddress(remoteAddress);
		session.setUserName(userName);
		session.setLoginTime(dateTimeService.getCurrentDateAndTime().toInstant());
		session = accountService.saveSession(session);
		logger.info("saved session for user {}:{}", userName, session);
	}

}
