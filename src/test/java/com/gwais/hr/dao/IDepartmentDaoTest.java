package com.gwais.hr.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gwais.hr.model.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class IDepartmentDaoTest {

	@Autowired
	IDepartmentDao departmentDao;
	
	@BeforeAll
	static void startInitialSteps() {
		// Add whatever you want to run before all test
	}
	
	@AfterAll
	static void finalEndingSteps() {
		// Add whatever you want to run after all test
	}
	
	@BeforeEach
	void initialSteps() {
		// Add whatever you want to run before each test case
	}
	
	@AfterEach
	void endingSteps() {
		// Add whatever you want to run after each test case
	}
	
	// @Disabled can be used to ignore a test case
	
	@Test
	void testFindByIdLong() {
		Long id = 10l;
		Optional<Department> dept = departmentDao.findById(id);
		assertTrue(dept.isPresent());
		
		Department dept2 = departmentDao.getOne(id);
		assertNotNull(dept2);
	}
	
	@Test //(expected = EntityNotFoundException.class)
	void testFindByWrongIdLong() throws Exception {
		Long id = 5l;
		
		Exception exception = assertThrows(EntityNotFoundException.class, () -> {
			Department dept = departmentDao.getOne(id);
			assertNotNull(dept);
			Long readId = dept.getDeptno();
			String readDname = dept.getDname(); // this will cause the exception
			System.out.print("deptID = " + readId + ", Name = " + readDname);
	    });
		 
	    String expectedMessage = "Unable to find";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage)); // check the exception message is right
	}

	@Test
	void testFindByDnameLikeIgnoreCase() {
		String departmentNameKeyword = "sal%";
		Long expectedId = 30l;
		
		List<Department> depts = departmentDao.findByDnameLikeIgnoreCase(departmentNameKeyword);
		assertNotNull(depts);
		assertTrue(depts.size() > 0);
		Long readId = depts.get(0).getDeptno();
		assertEquals(expectedId, readId, "First ID does not match");
	}

	@Test
	void testFindByLocLikeIgnoreCase() {
		String locNameKeyword = "%o%";
		Long expectedId = 10l;
		
		List<Department> depts = departmentDao.findByLocLikeIgnoreCaseOrderByDeptno(locNameKeyword);
		assertNotNull(depts);
		assertTrue(depts.size() == 3);
		Long readId = depts.get(0).getDeptno();
		assertEquals(expectedId, readId, "First ID does not match");
	}


	@Test
	void testInsertAndDeleteDepartment() {
		Department deptEntity = new Department();
		String dname = "IT";
		deptEntity.setDname(dname);
		String loc = "SAN JOSE";
		deptEntity.setLoc(loc);
		
		Department savedDept = departmentDao.save(deptEntity);
		assertNotNull(savedDept);
		Long readId = savedDept.getDeptno();
		assertTrue(readId > 0);
		System.out.println("read ID : " + readId);
		
		departmentDao.delete(savedDept);
		
		Optional<Department> checkedDepts = departmentDao.findById(readId);
		assertTrue(!checkedDepts.isPresent());		
	}

}
