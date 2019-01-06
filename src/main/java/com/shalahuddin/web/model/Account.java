package com.shalahuddin.web.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shalahuddin.web.common.Auditable;

@Entity
@Table(name = "ADM_USER")
public class Account extends Auditable {
	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -7213685025515779662L;

	@Id
	@Column(name="USER_ID", length=50)
	private String userId;

	@Column(name="NAME", length=300)
	private String name;

	@Column(name="EMAIL", unique = true, length=100, nullable=false)
	private String email;

	@Column(name="PASSWORD", length=100, nullable=false)
	@JsonIgnore
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date dateOfBirth;

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="ROLE_CODE")
	private Role role;

	@Column(name="IS_ACTIVE", nullable=false)
	private boolean active;

	@Column(name="IS_NON_LOCKED", nullable=false)
	private boolean accountNonLocked;

	@Column(name = "LAST_LOGIN", nullable=true)
	private Instant lastLogin;

	@Column(name = "FAIL_COUNTER")
	private Integer failCounter;

	public Account() {
		super();
		this.accountNonLocked = true;
	}

	public Account(String userId, String email, String password, Role role) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.role = role;
		this.active = true;
		this.accountNonLocked = true;
	}

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
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

	public Integer getFailCounter() {
		return failCounter;
	}

	public void setFailCounter(Integer failCounter) {
		this.failCounter = failCounter;
	}

	public Instant getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Instant lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Account other = (Account) obj;
		if (active != other.active) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", dateOfBirth=");
		builder.append(dateOfBirth);
		builder.append(", role=");
		builder.append(role==null?null:role.getId());
		builder.append(", active=");
		builder.append(active);
		builder.append(", accountNonLocked=");
		builder.append(accountNonLocked);
		builder.append(", lastLogin=");
		builder.append(lastLogin);
		builder.append(", failCounter=");
		builder.append(failCounter);
		builder.append("]");
		return builder.toString();
	}

}
