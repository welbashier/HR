package com.gwais.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwais.hr.controller.DepartmentDto;
import com.gwais.hr.dao.IDepartmentDao;
import com.gwais.hr.model.Department;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	IDepartmentDao departmentDao;
	
	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> allDepts = departmentDao.findAll();
		return convert(allDepts);
	}

	@Override
	public DepartmentDto getOneDepartment(Long id) {
		// TODO Auto-generated method stub
		return null;
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
	
	//========================================================


	private List<DepartmentDto> convert(List<Department> allDepts) {
		List<DepartmentDto> deptList = new ArrayList<>();
		for (Department dept: allDepts) {
			DepartmentDto deptDto = new DepartmentDto();
			deptDto.setDeptno(dept.getDeptno());
			deptDto.setDname(dept.getDname());
			deptDto.setLoc(dept.getLoc());
			deptList.add(deptDto);
		}
		return deptList;
	}

}
