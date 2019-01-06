package com.shalahuddin.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shalahuddin.web.model.Account;
import com.shalahuddin.web.repository.AccountRepository;

@RestController
public class RestAccountController {
	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("account/current")
	@ResponseStatus(value = HttpStatus.OK)
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Account currentAccount(Principal principal) {
		Assert.notNull(principal);
		return accountRepository.findOneByEmail(principal.getName());
	}

	@GetMapping("account/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public Account account(@PathVariable("id") String id) {
		return accountRepository.findOne(id);
	}
}
