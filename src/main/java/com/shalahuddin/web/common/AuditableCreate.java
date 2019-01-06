/**
 *
 */
package com.shalahuddin.web.common;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableCreate extends BaseObject implements IAuditableCreate {
	@Column(name = "CREATED_DATE", nullable=true, updatable=false)
	@CreatedDate
	private Instant createdDateTime;
	@Column(name = "CREATED_BY", length = 50, nullable=true, updatable=false)
	@CreatedBy
	private String createdBy;
	@Version
	@Column(name="VERSION")
	private Integer version;
	@Override
	public Instant getCreatedDateTime() {
		return createdDateTime;
	}
	@Override
	public void setCreatedDateTime(Instant createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	@Override
	public String getCreatedBy() {
		return createdBy;
	}
	@Override
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}
