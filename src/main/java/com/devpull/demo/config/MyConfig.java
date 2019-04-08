package com.devpull.demo.config;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= "com.devpull.demo")
@PropertySource("classpath:application.properties")
public class MyConfig {
	
//	@Autowired
//	private Environment env;
//	
//	
//	
//	@Bean
//	public DataSource dataSource() {
//		
//		
//	}
//	
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	
//	@Bean
//	public DataSource dataSource() {
//		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//      dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
//      dataSource.setUsername(env.getProperty("jdbc.username"));
//      dataSource.setPassword(env.getProperty("jdbc.password"));
//	
//		return dataSource;
//	}
	
}
