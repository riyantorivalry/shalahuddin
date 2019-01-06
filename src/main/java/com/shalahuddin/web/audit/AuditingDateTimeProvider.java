/**
 *
 */
package com.shalahuddin.web.audit;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.data.auditing.DateTimeProvider;

import com.shalahuddin.web.service.DateTimeService;

public class AuditingDateTimeProvider implements DateTimeProvider {
	private final DateTimeService dateTimeService;

	public AuditingDateTimeProvider(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}
	/* (non-Javadoc)
	 * @see org.springframework.data.auditing.DateTimeProvider#getNow()
	 */
	@Override
	public Calendar getNow() {
		return GregorianCalendar.from(dateTimeService.getCurrentDateAndTime());
	}

}
