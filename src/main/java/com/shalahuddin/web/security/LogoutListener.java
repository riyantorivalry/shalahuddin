/**
 *
 */
package com.shalahuddin.web.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.shalahuddin.web.service.AccountService;

@Component
public class LogoutListener implements	ApplicationListener<SessionDestroyedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(LogoutListener.class);
	@Autowired
	private AccountService accountService;
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		logger.info("session destroyed={}", event.getId());
		String userName = null;
		List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
		if(lstSecurityContext != null && !lstSecurityContext.isEmpty()){
			Object principal = lstSecurityContext.get(0).getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				userName = ((UserDetails)principal).getUsername();
			} else {
				userName = principal.toString();
			}
			accountService.removeSession(userName);
			logger.info("logout for user {}", userName);
		}
	}

}
