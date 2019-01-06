/**
 *
 */
package com.shalahuddin.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shalahuddin.web.service.AccountService;

@Component("wsAuthenticationProvider")
public class WsAuthenticationProvider implements AuthenticationProvider{
	private static Logger logger = LoggerFactory.getLogger(WsAuthenticationProvider.class);
	@Autowired
	private AccountService accountService;

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		UserDetails user = null;
		String username = String.valueOf(auth.getPrincipal());
		String password = String.valueOf(auth.getCredentials());
		logger.info("username:" + username);

		// TODO place authenticate using web service here

		try{
			user = accountService.loadUserByUsername(username);
		}catch(UsernameNotFoundException e){
			logger.error("username "+username+" not found");
			throw new BadCredentialsException("Username not found");
		}

		if(user != null){
			// Return an authenticated token, containing user data and
			// authorities
			return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities()) ;
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
