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

import com.gwais.hr.service.IEmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	@GetMapping("/Status")
	public String status() {
		return "Up";
	}

	@GetMapping("")
	public List<EmployeeDto> employeeAPINo10() {
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return employees;
	}

	@GetMapping("/{id}")
	public EmployeeDto employeeAPINo1(@PathVariable Long id) {
		EmployeeDto readEmployee = employeeService.getOneEmployee(id);
		return readEmployee;
	}

	@PostMapping("/")
	public EmployeeDto employeeAPINo12(@RequestBody EmployeeDto changedEmployee) {
		EmployeeDto addedEmployee = employeeService.addEmployee(changedEmployee);
		return addedEmployee;
	}

	@PutMapping("/{id}") // may not be needed
	public EmployeeDto employeeAPINo3(
			@RequestBody EmployeeDto changedEmployee,
			@PathVariable(name = "id", required = true) Long id) {
		return employeeService.modifyEmployee(changedEmployee, id);
	}

	@DeleteMapping("/{id}")
	public String employeeAPINo4(@PathVariable Long id) {
		return employeeService.removeEmployee(id);
	}

}
