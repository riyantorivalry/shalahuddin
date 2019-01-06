package com.shalahuddin.web.config;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.shalahuddin.web.security.LimitLoginAuthenticationProvider;
import com.shalahuddin.web.service.AccountService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountService accountService;
	@Autowired
	@Qualifier("customAuthenticationProvider")
	AuthenticationProvider authenticationProvider;
	// use below auth provider for custom authentication
	//    @Autowired
	//	@Qualifier("wsAuthenticationProvider")
	//	AuthenticationProvider authenticationProvider;

	@Value("${ldap.domain}")
	private String ldapDomain;
	@Value("${ldap.url}")
	private String ldapURL;
	@Value("${ldap.auth}")
	private boolean ldapAuth;

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key", accountService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		// Spring Security should completely ignore URLs starting with /resources/
		.antMatchers("/resources/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		if(ldapAuth){
			auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
		} else {
			if(authenticationProvider instanceof LimitLoginAuthenticationProvider){
				((LimitLoginAuthenticationProvider) authenticationProvider).setPasswordEncoder(passwordEncoder());
			}
			auth
			.authenticationProvider(authenticationProvider)
			.eraseCredentials(true);
			//            .userDetailsService(accountService)
			//            .passwordEncoder(passwordEncoder());
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "/favicon.ico", "/resources/**", "/signup", "/about").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/signin")
		.permitAll()
		.failureUrl("/signin?error=1")
		.loginProcessingUrl("/authenticate")
		.and()
		.logout()
		.logoutUrl("/logout")
		.permitAll()
		.logoutSuccessUrl("/signin?logout")
		.and()
		.rememberMe()
		.rememberMeServices(rememberMeServices())
		.key("remember-me-key");
	}

	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		logger.debug("ActiveDirectoryLdapAuthenticatinProvider: domain={}, URL={}", ldapDomain, ldapURL);
		ActiveDirectoryLdapAuthenticationProvider authenticationProvider =
				new ActiveDirectoryLdapAuthenticationProvider(ldapDomain, ldapURL);

		authenticationProvider.setConvertSubErrorCodesToExceptions(true);
		authenticationProvider.setUseAuthenticationRequestCredentials(true);
		authenticationProvider.setUserDetailsContextMapper(userDetailsContextMapper());

		return authenticationProvider;
	}

	@Bean
	public UserDetailsContextMapper userDetailsContextMapper() {
		return new LdapUserDetailsMapper() {
			@Override
			public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
				UserDetails userDetails = accountService.loadUserByUsername(username);
				logger.debug("username={}, user details={}", username, userDetails);
				return userDetails;
			}
		};
	}

	/**
	 * This is essential to make sure that the Spring Security session registry
	 * is notified when the session is destroyed.
	 * @return
	 */
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}