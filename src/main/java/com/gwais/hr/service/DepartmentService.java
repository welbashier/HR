package com.gwais.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwais.hr.dao.IDepartmentDao;
import com.gwais.hr.model.Department;

import dto.DepartmentDto;
import dto.EmployeeDto;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	IDepartmentDao departmentDao;

	@Autowired
	IEmployeeService employeeService;
	
	
	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> allDepts = departmentDao.findAll();
		return convert(allDepts);
	}

	@Override
	public DepartmentDto getOneDepartment(Long id) {
		Department readDept = departmentDao.getOne(id);
		return convert(readDept);
	}

	@Override
	public DepartmentDto addDepartment(DepartmentDto changedDepartmentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeDepartment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDto modifyDepartment(DepartmentDto changedDepartmentDto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDto getAllDepartmentWithEmployees(Long id) {
		DepartmentDto readDept = getOneDepartment(id);
		List<EmployeeDto> employeesList = employeeService.getEmployeesOfDepartment(id);
		readDept.setEmployeesList(employeesList);
		return readDept;
	}
	
	//========================================================


	private List<DepartmentDto> convert(List<Department> allDepts) {
		List<DepartmentDto> deptList = new ArrayList<>();
		for (Department dept: allDepts) {
			DepartmentDto deptDto = convert(dept);
			deptList.add(deptDto);
		}
		return deptList;
	}

	private DepartmentDto convert(Department dept) {
		DepartmentDto deptDto = new DepartmentDto();
		deptDto.setDeptno(dept.getDeptno());
		deptDto.setDname(dept.getDname());
		deptDto.setLoc(dept.getLoc());
		return deptDto;
	}
}
