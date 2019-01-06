package com.shalahuddin.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.shalahuddin.web.Application;
import com.shalahuddin.web.service.DateTimeService;
import com.shalahuddin.web.service.impl.CurrentTimeDateTimeService;

@Configuration
@PropertySource("classpath:persistence.properties")
@PropertySource("classpath:application.properties")
@PropertySource("classpath:ldap.properties")
@ComponentScan(basePackageClasses = Application.class)
class ApplicationConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	DateTimeService currentTimeDateTimeService() {
		return new CurrentTimeDateTimeService();
	}
}