package com.shalahuddin.web.common;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable extends BaseObject implements IAuditable{
	@Column(name = "CREATED_DATE", nullable=true, updatable=false)
	@CreatedDate
	private Instant createdDateTime;
	@Column(name = "CREATED_BY", length = 50, nullable=true, updatable=false)
	@CreatedBy
	private String createdBy;
	@Column(name = "UPDATED_DATE", nullable=true)
	@LastModifiedDate
	private Instant modifiedDateTime;
	@Column(name = "UPDATED_BY", length = 50, nullable=true)
	@LastModifiedBy
	private String modifiedBy;
	@Version
	@Column(name="VERSION")
	private Integer version;

	@Override
	public Instant getCreatedDateTime() {
		return createdDateTime;
	}


	@Override
	public String getCreatedBy() {
		return createdBy;
	}


	@Override
	public Instant getModifiedDateTime() {
		return modifiedDateTime;
	}


	@Override
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setCreatedDateTime(Instant createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public void setModifiedDateTime(Instant modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	@Override
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

}
