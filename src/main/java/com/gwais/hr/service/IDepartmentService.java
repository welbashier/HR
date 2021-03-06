package com.gwais.hr.service;

import java.util.List;

import dto.DepartmentDto;

public interface IDepartmentService {

	List<DepartmentDto> getAllDepartments();

	DepartmentDto getOneDepartment(Long id);

	DepartmentDto addDepartment(DepartmentDto changedDepartmentDto);

	String removeDepartment(Long id);

	DepartmentDto modifyDepartment(DepartmentDto changedDepartmentDto, Long id);

	DepartmentDto getAllDepartmentWithEmployees(Long id);
}
