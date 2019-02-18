package com.shalahuddin.web.config;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

import com.shalahuddin.web.Application;
import com.shalahuddin.web.audit.AuditingDateTimeProvider;
import com.shalahuddin.web.audit.UsernameAuditorAware;
import com.shalahuddin.web.service.DateTimeService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider", auditorAwareRef="auditorProvider")
@EnableJpaRepositories(basePackageClasses = Application.class)
class JpaConfig {
	private static final Logger logger = LoggerFactory.getLogger(JpaConfig.class);

	@Value("${dataSource.driverClassName}")
	private String driver;
	@Value("${dataSource.url}")
	private String url;
	@Value("${dataSource.username}")
	private String username;
	@Value("${dataSource.password}")
	private String password;
	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddlAuto;
	@Value("${hibernate.show_sql}")
	private String showSql;
	@Value("${hibernate.format_sql}")
	private String formatSql;
	@Value("${hibernate.use_sql_comments}")
	private String useSqlComments;

	@Value("${dataSource.jndiName}")
	private String dataSourceJndiName;

	@Bean
	public DataSource dataSource() {
		if(StringUtils.isNotBlank(dataSourceJndiName)){
			logger.info("lookup datasource={}", dataSourceJndiName);
			Hashtable<String, String> env = new Hashtable<>();
			// uncomment code below only if use weblogic datasource
			/*
			env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			env.put(Context.PROVIDER_URL, "t3://localhost:7001");
			 */
			logger.info("environment={}", env);
			DataSource dataSource = null;
			try {
				Context context = new InitialContext(env);
				logger.info("context={}", context);
				dataSource = (DataSource) context.lookup(dataSourceJndiName);
			} catch (NamingException e) {
				logger.error("failed get dataSourceJndiName=" + dataSourceJndiName,
						e);
			}
			return dataSource;
		} else {
			HikariConfig config = new HikariConfig();
			config.setDriverClassName(driver);
			config.setJdbcUrl(url);
			config.setUsername(username);
			config.setPassword(password);
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			config.addDataSourceProperty("useServerPrepStmts", "true");
			// uncomment config below only for db oracle
			//	        config.setConnectionTestQuery("select sysdate from dual");

			return new HikariDataSource(config);
		}
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		try{

			entityManagerFactoryBean.setDataSource(dataSource);

			String entities = ClassUtils.getPackageName(Application.class);
			String converters = ClassUtils.getPackageName(Jsr310JpaConverters.class);
			entityManagerFactoryBean.setPackagesToScan(entities, converters);

			entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

			Properties jpaProperties = new Properties();
			jpaProperties.put(Environment.DIALECT, dialect);
			jpaProperties.put(Environment.HBM2DDL_AUTO, hbm2ddlAuto);
			jpaProperties.put(Environment.SHOW_SQL, showSql);
			jpaProperties.put(Environment.FORMAT_SQL, formatSql);
			jpaProperties.put(Environment.USE_SQL_COMMENTS, useSqlComments);
			entityManagerFactoryBean.setJpaProperties(jpaProperties);
		}catch(Exception e){
			logger.error("failed create entityManagerFactory", e);
		}
		return entityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public DateTimeProvider dateTimeProvider(DateTimeService dateTimeService){
		return new AuditingDateTimeProvider(dateTimeService);
	}

	@Bean
	AuditorAware<String> auditorProvider() {
		return new UsernameAuditorAware();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
}
