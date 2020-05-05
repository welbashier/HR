package com.gwais.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwais.hr.service.IDepartmentService;

@RestController
@RequestMapping("/Department")
public class DepartmentController {
	
	@Autowired
	IDepartmentService departmentService;
	
	@GetMapping("")
	public List<DepartmentDto> noName01() {
		List<DepartmentDto> Departments = departmentService.getAllDepartments();
		return Departments;
	}
	
	//TODO: all the rest of operations

}
