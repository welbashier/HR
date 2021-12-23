package com.gwais.hr.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class HrSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Resource
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
	
	/*
	 * Normally, POST mappings are filtered by CSRFfilters. Although it is not
	 * recommended in the production environment, you can disable CSRF filter simply
	 * using for learning. This method also causes 403 error for some other pages. 
	 */
	 @Override 
	 protected void configure(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity
	         .formLogin() // use form-based login instead of Basic Authentication
	         .and()
		         .authorizeRequests()
		         .antMatchers("/admin**").access("hasRole('ADMIN')")
		         .anyRequest().authenticated() // Any other resources needs to be authenticated
	         .and()
	         	.anonymous().disable(); // disable anonymous authentication/users
	 }
	 
}