package com.gwais.hr.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

@Configuration
@EnableWebSecurity
public class HrSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/Employee/home").access("hasRole('USER') or hasRole('ADMIN')")
            .antMatchers("/Employee/admin").access("hasRole('ADMIN')")
            .anyRequest().authenticated()
            .and().formLogin().permitAll()
            .and().logout().permitAll()
            .and().exceptionHandling().accessDeniedPage("/403")
            ;
    }
    
}