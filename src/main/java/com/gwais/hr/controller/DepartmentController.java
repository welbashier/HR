package com.gwais.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwais.hr.dto.DepartmentDto;
import com.gwais.hr.service.DepartmentService;

@RestController
@RequestMapping("/Department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("")
	public List<DepartmentDto> anyName01() {
		List<DepartmentDto> Departments = departmentService.getAllDepartments();
		return Departments;
	}

	
	@GetMapping("/{id}")
	public DepartmentDto anyName02(@PathVariable Long id) {
		DepartmentDto readDepartment = departmentService.getOneDepartment(id);
		return readDepartment;
	}

	
	@GetMapping("/{id}/Employees")
	public DepartmentDto anyName03(@PathVariable Long id) {
		DepartmentDto readDepartment = departmentService.getAllDepartmentWithEmployees(id);
		return readDepartment;
	}
	
	//TODO: all the rest of operations

}
