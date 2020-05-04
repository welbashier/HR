package com.gwais.hr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwais.hr.controller.EmployeeDto;
import com.gwais.hr.dao.IEmployeeDao;
import com.gwais.hr.model.Employee;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	IEmployeeDao employeeDao;

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return convert(employeeDao.findAll());
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
		empDto.setEmpno(oneEmployee.getEmpno());
		empDto.setEname(oneEmployee.getEname());
		empDto.setJob(oneEmployee.getJob());
		empDto.setMgr(oneEmployee.getMgr());
		empDto.setSal(oneEmployee.getSal());
		empDto.setComm(oneEmployee.getComm());
		empDto.setDeptno(oneEmployee.getDeptno());
		empDto.setHiredate(oneEmployee.getHiredate());
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

		Date utilHiredate = changedEmployeeDto.getHiredate();
		java.sql.Date sqlDate = new java.sql.Date(utilHiredate.getTime());
		emp.setHiredate(sqlDate);
		return emp;
	}
}
