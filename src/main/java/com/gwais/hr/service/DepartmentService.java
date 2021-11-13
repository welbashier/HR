package com.gwais.hr.service;

import java.util.List;

import com.gwais.hr.dto.DepartmentDto;

public interface DepartmentService {

	List<DepartmentDto> getAllDepartments();

	DepartmentDto getOneDepartment(Long id);

	DepartmentDto addDepartment(DepartmentDto changedDepartmentDto);

	String removeDepartment(Long id);

	DepartmentDto modifyDepartment(DepartmentDto changedDepartmentDto, Long id);

	DepartmentDto getAllDepartmentWithEmployees(Long id);
}
