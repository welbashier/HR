package com.gwais.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gwais.hr.service.IEmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	// accessed by: http://localhost:8011/Employee/Status
	@GetMapping("/Status")
	public String status() {
		return "Up";
	}

	// accessed by: http://localhost:8011/Employee
	@GetMapping("")
	public List<EmployeeDto> employeeAPINo10() {
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return employees;
	}

	// accessed by: http://localhost:8011/Employee/7566
	@GetMapping("/{id}")
	public EmployeeDto employeeAPINo1(@PathVariable Long id) {
		EmployeeDto readEmployee = employeeService.getOneEmployee(id);
		return readEmployee;
	}

	// accessed by API client (e.g. Postman) to post/add employees
	@PostMapping("/")
	public EmployeeDto employeeAPINo12(@RequestBody EmployeeDto changedEmployee) {
		EmployeeDto addedEmployee = employeeService.addEmployee(changedEmployee);
		return addedEmployee;
	}

	// accessed by API client (e.g. Postman) to put/update employees
	@PutMapping("/{id}") // may not be needed
	public EmployeeDto employeeAPINo3(
			@RequestBody EmployeeDto changedEmployee,
			@PathVariable(name = "id", required = true) Long id) {
		return employeeService.modifyEmployee(changedEmployee, id);
	}

	// accessed by API client (e.g. Postman) to delete employees
	@DeleteMapping("/{id}")
	public String employeeAPINo4(@PathVariable Long id) {
		return employeeService.removeEmployee(id);
	}
	
	/*
	 * Returning static HTML pages/views
	 */
	
	@GetMapping(value = { "/home"})
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeeHome");
		return model;
	}
	
	@GetMapping(value = { "/admin"})
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeeAdmin");
		return model;
	}

}
