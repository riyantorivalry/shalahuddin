package com.shalahuddin.web.common;

import java.time.Instant;

public interface IAuditable {
	Instant getCreatedDateTime();
	String getCreatedBy();
	Instant getModifiedDateTime();
	String getModifiedBy();
	void setCreatedDateTime(Instant createdDateTime);
	void setCreatedBy(String createdBy);
	void setModifiedDateTime(Instant modifiedDateTime);
	void setModifiedBy(String modifiedBy);
}
