package com.shalahuddin.web.form;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shalahuddin.web.validator.EmailExists;

public class SignupForm {
	@NotBlank
	private String userId;

	@NotBlank
	private String name;

	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date dateOfBirth;

	@NotBlank
	@Email
	@EmailExists
	private String email;

	@JsonIgnore
	private String password;

	@NotBlank
	private String role;

	private boolean active;

	private boolean accountNonLocked;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignupForm [userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", dateOfBirth=");
		builder.append(dateOfBirth);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", role=");
		builder.append(role);
		builder.append(", active=");
		builder.append(active);
		builder.append(", accountNonLocked=");
		builder.append(accountNonLocked);
		builder.append("]");
		return builder.toString();
	}
}
