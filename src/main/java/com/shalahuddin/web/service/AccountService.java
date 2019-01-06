package com.shalahuddin.web.service;

import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shalahuddin.web.model.Account;
import com.shalahuddin.web.model.ActiveSession;
import com.shalahuddin.web.model.Menu;
import com.shalahuddin.web.model.Role;
import com.shalahuddin.web.repository.AccountRepository;
import com.shalahuddin.web.repository.ActiveSessionRepository;
import com.shalahuddin.web.repository.MenuRepository;
import com.shalahuddin.web.repository.RoleRepository;
import com.shalahuddin.web.util.IConstants;

@Service("accountService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private ActiveSessionRepository activeSessionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void clearActiveSession() {
		// clear active session
		logger.info("clear active sessions");
		activeSessionRepository.deleteAllInBatch();
	}

	@PostConstruct
	public void initialize() {
		Role roleUser = new Role("ROLE_USER", "USER");
		Role roleAdmin = new Role("ROLE_ADMIN", "ADMINISTRATOR");
		if(!roleRepository.exists("ROLE_USER")){
			roleUser = saveRole(roleUser);
		}
		if(!roleRepository.exists("ROLE_ADMIN")){
			roleAdmin = saveRole(roleAdmin);
		}
		if(!accountRepository.exists("user")){
			saveAccount(new Account("user","user@example.com", "demo", roleUser));
		}
		if(!accountRepository.exists("admin")){
			saveAccount(new Account("admin","admin@example.com", "admin", roleAdmin));
		}

		String[] menuCodes = {"MENU_USR_MGMT","SUBMENU_USER","SUBMENU_ROLE","MENU_EXAMPLE","SUBMENU_UPLOAD", "MENU_ABOUT"};
		String[] menuNames = {"User Management","User","Role","Example","Upload File","About"};
		String[] accessLinks = {"#","user","role","#","upload","about"};
		int[] positions = {1,1,2,2,1,3};

		for(int i=0; i<menuCodes.length; i++) {
			Menu menu;
			if(!menuRepository.exists(menuCodes[i])){
				menu = new Menu();
				menu.setId(menuCodes[i]);
				menu.setName(menuNames[i]);
				menu.setAccessLink(accessLinks[i]);
				menu.setActive(true);
				menu.setPosition(positions[i]);
				if(i==1 | i==2){
					menu.setParentId(menuCodes[0]);
				}
				if(i==4){
					menu.setParentId(menuCodes[3]);
				}

				logger.debug("initialize menu {}", menu);
				menu = saveMenu(menu);
				logger.debug("saved menu={}", menu);
			} else {
				menu = menuRepository.findOne(menuCodes[i]);
			}
		}

		roleAdmin = roleRepository.findOne("ROLE_ADMIN");
		if(roleAdmin.getMenus() == null || roleAdmin.getMenus().size() == 0){
			logger.debug("add initial role-menu to role admin");
			roleAdmin.getMenus().add(menuRepository.findOneByIdAndActive("MENU_USR_MGMT", true));
			roleAdmin.getMenus().add(menuRepository.findOneByIdAndActive("SUBMENU_USER", true));
			roleAdmin.getMenus().add(menuRepository.findOneByIdAndActive("SUBMENU_ROLE", true));
			saveRole(roleAdmin);
			logger.debug("ROLE_ADMIN has been initialized");
		}
	}

	@Transactional
	public Account saveAccount(Account account) {
		return saveAccount(account, true);
	}

	@Transactional
	public Account saveAccount(Account account, boolean encodePassword) {
		if(StringUtils.isNotBlank(account.getPassword())){
			if(encodePassword){
				account.setPassword(passwordEncoder.encode(account.getPassword()));
			}
		}
		accountRepository.save(account);
		return account;
	}

	@Transactional
	public Role saveRole(Role role){
		return roleRepository.save(role);
	}

	@Transactional
	public Menu saveMenu(Menu menu){
		return menuRepository.save(menu);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findOneByUserIdAndActive(username, true);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}

	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}

	@Transactional
	public void resetLoginFailCounter(String userId){
		accountRepository.updateLoginFailCounter(userId, 0);
		// unlock user
		accountRepository.setAccountNonLocked(userId, true);
	}

	@Transactional
	public void updateLastLogin(String userId){
		accountRepository.updateLastLogin(userId, new Date());
	}

	@Transactional
	public void increaseLoginFailCounter(String userId){
		Account account = accountRepository.findOne(userId);
		logger.debug("account before increase={}", account);
		Integer failCounter = account.getFailCounter()==null?1:account.getFailCounter() + 1;
		accountRepository.updateLoginFailCounter(userId, failCounter);
		if(failCounter >= IConstants.MAX_LOGIN_FAILED_ATTEMPTS){
			// locked user
			accountRepository.setAccountNonLocked(userId, false);
		}
	}

	@Transactional
	public ActiveSession saveSession(ActiveSession session){
		return activeSessionRepository.save(session);
	}

	@Transactional
	public void removeSession(String userName){
		activeSessionRepository.deleteByUserName(userName);
	}

	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
	}

	/**
	 * create UserDetails instance with args: userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, GrantedAuthority
	 * @param account
	 * @return
	 */
	private User createUser(Account account) {
		return new User(account.getUserId(), account.getPassword(), account.isActive(), true, true, account.isAccountNonLocked(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(Account account) {
		if(account.getRole() != null){
			return new SimpleGrantedAuthority(account.getRole().getId());
		} else {
			return new SimpleGrantedAuthority("ROLE_ANONYMOUS");
		}
	}

}
