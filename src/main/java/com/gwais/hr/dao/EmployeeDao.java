package com.gwais.hr.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gwais.hr.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
	/*
	 * these methods are needed to be declared in order to be used. 
	 * implementation is provided by framework.
	 */
	
	public Employee findById(long id);

	public List<Employee> findByEnameLikeIgnoreCase(String empNameKeyword);

	public List<Employee> findByDeptno(Long deptNo);

	public List<Employee> findByHiredateGreaterThanEqual(Date hireDate);

	public List<Employee> findByJobLikeIgnoreCaseAndDeptno(String job, Long deptno);

}
