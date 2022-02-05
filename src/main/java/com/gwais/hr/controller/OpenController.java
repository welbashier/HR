package com.gwais.hr.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gwais.hr.dto.EmployeeDto;
import com.gwais.hr.service.EmployeeService;

@RestController
@RequestMapping("/Open")
public class OpenController {

	@Autowired
	EmployeeService employeeService;

	// accessed by: http://localhost:8011/Employee/Status
	@GetMapping("/Test")
	public String status() {
		return "Controller is up and accessible";
	}

	@PostMapping("/Post")
	public EmployeeDto registerEmployee(
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String userName,
			@RequestParam String city,
			@RequestParam String state,
			@RequestParam String zip,
			@RequestParam String agree) {
		EmployeeDto changedEmployee = new EmployeeDto();
		changedEmployee.setEname(firstName + " " + lastName);
		changedEmployee.setComm(0d);
		changedEmployee.setHiredate(new Date());
		changedEmployee.setJob("NewHire");
		changedEmployee.setSal(35000d);
		EmployeeDto addedEmployee = employeeService.addEmployee(changedEmployee);
		return addedEmployee;
	}
	
	/*
	 * try this later: 
	public EmployeeDto registerEmployee(@RequestBody EmployeeDto newEmployee)
	or
	public EmployeeDto registerEmployee(HttpServletRequest req)
	 *
	 */

	@PostMapping("/Post2")
	public EmployeeDto registerEmployee() {
		EmployeeDto changedEmployee = new EmployeeDto();
		EmployeeDto addedEmployee = employeeService.addEmployee(changedEmployee);
		return addedEmployee;
	}
}
