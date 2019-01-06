/**
 *
 */
package com.shalahuddin.web.service.impl;

import java.time.ZonedDateTime;

import com.shalahuddin.web.service.DateTimeService;

public class CurrentTimeDateTimeService implements DateTimeService {

	/* (non-Javadoc)
	 * @see id.co.quadras.springmvc.service.DateTimeService#getCurrentDateAndTime()
	 */
	@Override
	public ZonedDateTime getCurrentDateAndTime() {
		return ZonedDateTime.now();
	}

}
