/**
 *
 */
package com.shalahuddin.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {
	/**
	 * Folder location for storing files
	 */
	@Value("${fileUpload.dir}")
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


}
