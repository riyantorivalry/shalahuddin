/**
 *
 */
package com.shalahuddin.web.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.shalahuddin.web.common.BaseObject;

@Entity
@Table(name = "ADM_ACTIVE_SESSION")
public class ActiveSession extends BaseObject{
	/**
	 * serialVersion UID
	 */
	private static final long serialVersionUID = 3486549102191605203L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="SESSION_ID", length=36)
	private String id;
	@Column(name="USER_NAME", length=50, nullable=false, unique=true)
	private String userName;
	@Column(name="IP_ADDRESS", length=15)
	private String remoteAddress;
	@Column(name = "LOGIN_TIME",updatable=false)
	private Instant loginTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	public Instant getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Instant loginTime) {
		this.loginTime = loginTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((loginTime == null) ? 0 : loginTime.hashCode());
		result = prime * result
				+ ((remoteAddress == null) ? 0 : remoteAddress.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		ActiveSession other = (ActiveSession) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (loginTime == null) {
			if (other.loginTime != null) {
				return false;
			}
		} else if (!loginTime.equals(other.loginTime)) {
			return false;
		}
		if (remoteAddress == null) {
			if (other.remoteAddress != null) {
				return false;
			}
		} else if (!remoteAddress.equals(other.remoteAddress)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActiveSession [id=");
		builder.append(id);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", remoteAddress=");
		builder.append(remoteAddress);
		builder.append(", loginTime=");
		builder.append(loginTime);
		builder.append("]");
		return builder.toString();
	}


}
