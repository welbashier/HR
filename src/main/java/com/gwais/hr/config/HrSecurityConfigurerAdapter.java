package com.gwais.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebSecurity
public class HrSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/Employee/home")	.access("hasRole('ADMIN') or hasRole('USER')")
				.antMatchers("/Employee/admin")	.access("hasRole('ADMIN')")
				.antMatchers("/Employee/{id}")	.access("hasRole('USER')")
				.anyRequest().authenticated()
				.and()
				.formLogin().permitAll()
				.and()
				.logout().permitAll()
				;
	}

	// added to use the sec:authorize to check roles before displaying the contents
	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(sec); // Enable use of "sec" return templateEngine;
		return (templateEngine);
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("zoalsai").password(passwordEncoder().encode("tamaam")).roles("USER")
          .and()
          .withUser("modeer").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}