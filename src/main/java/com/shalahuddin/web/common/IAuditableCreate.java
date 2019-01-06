/**
 *
 */
package com.shalahuddin.web.common;

import java.time.Instant;

public interface IAuditableCreate {
	Instant getCreatedDateTime();
	String getCreatedBy();
	void setCreatedDateTime(Instant createdDateTime);
	void setCreatedBy(String createdBy);
}
