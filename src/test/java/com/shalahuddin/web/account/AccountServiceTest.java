package com.shalahuddin.web.account;

import static java.util.function.Predicate.isEqual;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shalahuddin.web.model.Account;
import com.shalahuddin.web.model.Role;
import com.shalahuddin.web.repository.AccountRepository;
import com.shalahuddin.web.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);

	@InjectMocks
	private AccountService accountService = new AccountService();

	@Mock
	private AccountRepository accountRepositoryMock;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	//	@Test
	public void shouldInitializeWithDemoUsers() {
		// act
		accountService.initialize();
		// assert
		verify(accountRepositoryMock, atLeast(2)).save(any(Account.class));
	}

	@Test
	public void shouldThrowExceptionWhenUserNotFound() {
		// arrange
		thrown.expect(UsernameNotFoundException.class);
		thrown.expectMessage("user not found");

		when(accountRepositoryMock.findOneByEmail("user@example.com")).thenReturn(null);
		// act
		accountService.loadUserByUsername("user");
	}

	@Test
	public void shouldReturnUserDetails() {
		// arrange
		Account demoUser = new Account("user","user@example.com", "demo", new Role("ROLE_USER","USER"));
		when(accountRepositoryMock.findOneByUserIdAndActive("user", true)).thenReturn(demoUser);
		logger.info("demoUser={}", demoUser);

		// act
		UserDetails userDetails = accountService.loadUserByUsername("user");
		logger.info("userDetails={}", userDetails);

		// assert
		assertThat(demoUser.getUserId()).isEqualTo(userDetails.getUsername());
		assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
		assertThat(hasAuthority(userDetails, demoUser.getRole().getId())).isTrue();
	}

	private boolean hasAuthority(UserDetails userDetails, String role) {
		return userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(isEqual(role));
	}
}
