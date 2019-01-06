package com.shalahuddin.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shalahuddin.web.exception.ConcurrentLoginException;

@Controller
public class SigninController {
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("signin")
	public ModelAndView signin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request, Locale locale) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			logger.debug("failed login");
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION", locale));
		}

		if (logout != null) {
			logger.debug("logged out successfully");
			model.addObject("message", "You've been logged out successfully.");
		}
		model.setViewName("signin/signin");
		return model;
	}

	@RequestMapping(value="logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		logger.debug("logout");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("authorities={}", auth.getAuthorities());
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/signin?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

	/**
	 * customize the error message
	 * @param request
	 * @param key
	 * @return
	 */
	private String getErrorMessage(HttpServletRequest request, String key, Locale locale){

		Exception exception =
				(Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			logger.debug("exception instanceof BadCredentialsException");
			error = messageSource.getMessage("BadCredentialsException", null, locale);
		}else if(exception instanceof LockedException) {
			logger.debug("exception instanceof LockedException");
			error = messageSource.getMessage("LockedException", null, locale);
		}else if(exception instanceof ConcurrentLoginException) {
			logger.debug("exception instanceof ConcurrentLoginException");
			error = messageSource.getMessage("ConcurrentLoginException", null, locale);
		}else{
			logger.debug("else");
			error = exception.getMessage();
		}
		logger.debug("error message = {}", error);

		return error;
	}
}
