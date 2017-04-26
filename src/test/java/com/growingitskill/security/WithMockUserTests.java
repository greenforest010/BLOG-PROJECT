package com.growingitskill.security;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class, 
        DependencyInjectionTestExecutionListener.class, 
        DirtiesContextTestExecutionListener.class, 
        TransactionalTestExecutionListener.class, 
        WithSecurityContextTestExecutionListener.class}) 
public class WithMockUserTests {
	
	@Autowired
	private MessageService messageService;
	
	@Test(expected=AuthenticationCredentialsNotFoundException.class)
	public void getMessageUnauthentifated() {
		messageService.getMessage();
	}
	
	@Test
	@WithMockUser()
	public void getMessageWithMockUser() {
		String message = messageService.getMessage();
		assertThat(message).contains("user");
	}
	
	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@ComponentScan(basePackageClasses = HelloMessgeService.class)
	static class SecurityTestConfig {
		
		@Autowired 
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
	        auth 
	            .inMemoryAuthentication() 
	                .withUser("user").password("password").roles("USER"); 
	    }
	}

}
