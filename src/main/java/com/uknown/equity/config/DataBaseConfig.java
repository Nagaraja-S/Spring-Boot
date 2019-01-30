package com.uknown.equity.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

//@Configuration
//@EnableJpaRepositories(basePackages="com.uknown.respository")
public class DataBaseConfig {

	/*@Autowired
	private Environment environment;
	
	@Bean
	public DataSource datasource() {
		DriverManagerDataSource driverMgrDatasource = new DriverManagerDataSource();
		
		driverMgrDatasource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
		driverMgrDatasource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		driverMgrDatasource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
		driverMgrDatasource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
		return driverMgrDatasource;	
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdaptor = new HibernateJpaVendorAdapter();
		vendorAdaptor.setDatabase(Database.MYSQL);
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.jdbc.batch_size", 500);
		jpaProperties.put("hibernate.order_inserts", true);
		jpaProperties.put("hibernate.order_updates", true);
		factory.setJpaProperties(jpaProperties);
		
		factory.setJpaVendorAdapter(vendorAdaptor);
		factory.setPackagesToScan("com.uknown.dto");
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}*/
	
	
}
