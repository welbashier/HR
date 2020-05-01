package com.gwais.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gwais.hr.model.Employee;

@Repository
public interface IEmployeeDao extends JpaRepository<Employee, Long> {

	public Employee findById(long id);
}
