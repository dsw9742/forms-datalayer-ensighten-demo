package com.douglaswhitehead;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
//@EnableWebSecurity
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public ServletRegistrationBean h2ServletRegistration() {
    	ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
    	registrationBean.addUrlMappings("/console/*");
    	return registrationBean;
    }
    
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
	        .inMemoryAuthentication()
	            .withUser("user").password("password").roles("USER");
    }*/
    
    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    	
    	@Autowired
    	private SecurityProperties security;
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
    		http
    			.authorizeRequests()
    				.antMatchers("/console/**")
    				.permitAll()
    				.and()
    			.authorizeRequests()
    				.anyRequest()
    				.anonymous()
    				.and()
    			.csrf()
    				.disable()
    			.headers()
    				.frameOptions()
    				.disable()
    			.formLogin()
    				.loginPage("/login")
    				.failureUrl("/login?error")
    				.permitAll();
    	}
    	
    	@Override
    	public void configure(AuthenticationManagerBuilder auth) throws Exception {
    		auth
    			.inMemoryAuthentication()
    				.withUser("admin")
    					.password("admin")
    					.roles("ADMIN", "USER")
    					.and()
    				.withUser("user")
    					.password("user")
    					.roles("USER");
    	}
    }
    
}
