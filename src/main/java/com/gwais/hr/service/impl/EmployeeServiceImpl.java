package com.gwais.hr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwais.hr.dao.EmployeeDao;
import com.gwais.hr.dto.EmployeeDto;
import com.gwais.hr.model.Employee;
import com.gwais.hr.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return convert(employeeDao.findAll());
	}

	@Override
	public List<EmployeeDto> getEmployeesOfDepartment(Long deptno) {
		return convert(employeeDao.findByDeptno(deptno));
	} 

	@Override
	public EmployeeDto getOneEmployee(Long id) {
		Employee oneEmployee = employeeDao.getOne(id);
		return convert(oneEmployee);
	}

	public EmployeeDto saveEmployee(EmployeeDto changedEmployeeDto) {
		Employee convertedChangedEmployee = convert(changedEmployeeDto);
		Employee savedEmployee = employeeDao.save(convertedChangedEmployee);
		return convert(savedEmployee);
	}

	@Override
	public EmployeeDto addEmployee(EmployeeDto changedEmployeeDto) {
		if (changedEmployeeDto != null && changedEmployeeDto.getEmpno() != null) {
			changedEmployeeDto.setEmpno(null);
		}
		return saveEmployee(changedEmployeeDto);
	}

	@Override
	public String removeEmployee(Long id) {
		String resultMessage = "";
		try {
			employeeDao.deleteById(id);
			resultMessage = "Record of id=" + id + " has been deleted.";
		} catch (Exception e) {
			resultMessage = "An error occured when deleting Record of id=" + id;
		}
		return resultMessage;
	}

	@Override
	public EmployeeDto modifyEmployee(EmployeeDto changedEmployeeDto, Long id) {
		Long changedEmployeeID = changedEmployeeDto.getEmpno();
		if (id == null || (changedEmployeeID != null && changedEmployeeID != id) || getOneEmployee(id) == null) {
			return null;
		}
		if (changedEmployeeID == null) {
			changedEmployeeDto.setEmpno(id);
		}
		return saveEmployee(changedEmployeeDto);
	}

	// ======================= private =========================

	private List<EmployeeDto> convert(List<Employee> employeesList) {
		List<EmployeeDto> dtoList = new ArrayList<>();
		for (Employee emp : employeesList) {
			dtoList.add(convert(emp));
		}
		return dtoList;
	}

	private EmployeeDto convert(Employee oneEmployee) {
		EmployeeDto empDto = new EmployeeDto();
		try {
			empDto.setEmpno(oneEmployee.getEmpno());
			empDto.setEname(oneEmployee.getEname());
			empDto.setJob(oneEmployee.getJob());
			empDto.setMgr(oneEmployee.getMgr());
			empDto.setSal(oneEmployee.getSal());
			empDto.setComm(oneEmployee.getComm());
			empDto.setDeptno(oneEmployee.getDeptno());
			empDto.setHiredate(oneEmployee.getHiredate());
		} catch(Exception e) {
			;
		}
		return empDto;
	}
	
	private Employee convert(EmployeeDto changedEmployeeDto) {
		Employee emp = new Employee();
		emp.setEmpno(changedEmployeeDto.getEmpno());
		emp.setEname(changedEmployeeDto.getEname().toUpperCase());
		emp.setJob(changedEmployeeDto.getJob().toUpperCase());
		emp.setMgr(changedEmployeeDto.getMgr());
		emp.setSal(changedEmployeeDto.getSal());
		emp.setComm(changedEmployeeDto.getComm());
		emp.setDeptno(changedEmployeeDto.getDeptno());

		Date utilHireDate = changedEmployeeDto.getHiredate();
		java.sql.Date sqlDate = new java.sql.Date(utilHireDate.getTime());
		emp.setHiredate(sqlDate);
		return emp;
	}
}
