package com.gwais.hr.service;

import java.util.List;

import com.gwais.hr.controller.EmployeeDto;

public interface IEmployeeService {

	List<EmployeeDto> getAllEmployees();

	List<EmployeeDto> getEmployeesOfDepartment(Long deptno);
	
	EmployeeDto getOneEmployee(Long id);

	EmployeeDto addEmployee(EmployeeDto changedEmployeeDto);

	String removeEmployee(Long id);

	EmployeeDto modifyEmployee(EmployeeDto changedEmployeeDto, Long id);

}