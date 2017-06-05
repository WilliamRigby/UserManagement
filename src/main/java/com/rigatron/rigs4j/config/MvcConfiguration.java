package com.rigatron.rigs4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.SQLException;

import com.rigatron.rigs4j.dao.UserDAO;


@Configuration
@ComponentScan(basePackages="com.rigatron.rigs4j")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{


	private static String JDBC_DATABASE_URL;
	private static String JDBC_DATABASE_USERNAME;
	private static String JDBC_DATABASE_PASSWORD;
	private java.sql.Connection connection;



	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}



	@Bean
	public BasicDataSource dataSource() throws URISyntaxException {

		GetDatabaseVariables();

		try {
			DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
			connection = DriverManager.getConnection(JDBC_DATABASE_URL);
		}
		catch(ClassNotFoundException e1) {

		}
		catch(InstantiationException e2) {

		}
		catch(IllegalAccessException e3) {

		}
		catch(SQLException e4) {

		}

		BasicDataSource basicDataSource = new BasicDataSource();

		basicDataSource.setUrl(JDBC_DATABASE_URL);
		basicDataSource.setUsername(JDBC_DATABASE_USERNAME);
		basicDataSource.setPassword(JDBC_DATABASE_PASSWORD);

		return basicDataSource;
	}

	@Bean
	public UserDAO getUserDAO() {

		UserDAO users = null;
		try {
			users = new UserDAO(dataSource());
		}
		catch(URISyntaxException e) {

		}
		return users;
	}



	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");		
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	private void GetDatabaseVariables() {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec("heroku run env");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				if(line.contains("JDBC_DATABASE_URL=") == true) {
					String[] strings = line.split("DATABASE_URL=");
					JDBC_DATABASE_URL = strings[1];
				}
				else if(line.contains("JDBC_DATABASE_USERNAME=") == true) {
					String[] strings = line.split("USERNAME=");
					JDBC_DATABASE_USERNAME = strings[1];
				}
				else if(line.contains("JDBC_DATABASE_PASSWORD=") == true) {
					String[] strings = line.split("PASSWORD=");
					JDBC_DATABASE_PASSWORD = strings[1];
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
