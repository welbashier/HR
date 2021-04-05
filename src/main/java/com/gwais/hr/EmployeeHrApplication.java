package com.gwais.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true) /* apply access rules to methods */
public class EmployeeHrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeHrApplication.class, args);
	}

}
