package com.rigatron.rigs4j.web.config;

import com.rigatron.rigs4j.BL.DAOs.UserDAO;
import com.rigatron.rigs4j.BL.DAOs.UserRoleDAO;
import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserDAO;
import com.rigatron.rigs4j.BL.DAOs.interfaces.IUserRoleDAO;
import com.rigatron.rigs4j.BL.services.UserService;
import com.rigatron.rigs4j.BL.services.interfaces.IUserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.apache.commons.dbcp.BasicDataSource;
import java.util.Properties;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.rigatron.rigs4j.BL.DAOs")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");		
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.rigatron.rigs4j.BL.entities" });

        Properties properties =  new Properties();

        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.globally_quoted_identifiers", "true");

        sessionFactory.setHibernateProperties(properties);

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
        dataSource.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
        dataSource.setPassword(System.getenv("JDBC_DATABASE_PASSWORD"));

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator(){
        return new HibernateExceptionTranslator();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Bean
    public IUserDAO userDAO() {
	    UserDAO dao =  new UserDAO();

	    dao.setSessionFactory(sessionFactory().getObject());

	    return dao;
    }

    @Bean
    public IUserRoleDAO userRoleDAO() {
        UserRoleDAO dao =  new UserRoleDAO();

        dao.setSessionFactory(sessionFactory().getObject());

        return dao;
    }

    @Bean
    public IUserService userService() {
	    UserService userService =  new UserService();

	    userService.setUserDAO(userDAO());
        userService.setUserRoleDAO(userRoleDAO());

	    return userService;
    }
}
