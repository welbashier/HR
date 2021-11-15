package com.gwais.hr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gwais.hr.model.Department;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Long> {
	/*
	 * these methods are needed to be declared in order to be used. 
	 * implementation is provided by framework.
	 */
	
	public Department findById(long id);

	public List<Department> findByDnameLikeIgnoreCase(String empNameKeyword);

	public List<Department> findByLocLikeIgnoreCaseOrderByDeptno(String loc);

}
