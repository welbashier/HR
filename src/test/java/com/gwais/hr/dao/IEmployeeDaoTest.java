package com.gwais.hr.dao;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.gwais.hr.model.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
final public class IEmployeeDaoTest {

	@Autowired
	IEmployeeDao employeeDao;

	@Test
	void test() {
		// fail("Not yet implemented");
		assertNotNull(employeeDao);
	}

	@Test
	void testSelectAll() {
		int expectedMinimumCount = 10;

		List<Employee> allEmployees = employeeDao.findAll();
		assertNotNull(allEmployees);
		assertTrue(allEmployees.size() > expectedMinimumCount);
	}

	@Test
	void testSelectOne() {
		Long givenEmpNo = 7839l;
		String expectedEmpName = "KING";
		String failingMessage = "the value of employee name doesn't match.";

		Employee oneEmployee = employeeDao.getOne(givenEmpNo);
		assertNotNull(oneEmployee);

		String actualEmpName = oneEmployee.getEname();
		assertEquals(expectedEmpName, actualEmpName, failingMessage);
	}

	@Test
	void testInsertOne_then_Delete() {
		String givenEmpName = "GEORGE";

		Employee givenEmployee = new Employee();
		givenEmployee.setEname(givenEmpName);

		Employee savedEmployee = employeeDao.save(givenEmployee);
		assertNotNull(savedEmployee);

		Long actualEmpNo = savedEmployee.getEmpno();
		assertTrue(actualEmpNo > 0);
		
		employeeDao.delete(savedEmployee);
		
		Optional<Employee> oneEmployee = employeeDao.findById(actualEmpNo);
		assertTrue(!oneEmployee.isPresent());
	}

	@Test
	void testUpdateOne() {
		// TODO
		String givenEmpName = "TOBEUPDATED";
		Long givenEmpNo = 7839l;
		String expectedEmpName = "KING";
		String failingMessage = "the value of employee name doesn't match.";

		// get the current employee name, and make sure it's KING
		Employee oneEmployee = employeeDao.getOne(givenEmpNo);
		assertNotNull(oneEmployee);

		String actualEmpName = oneEmployee.getEname();
		assertEquals(expectedEmpName, actualEmpName, failingMessage);
		
		// now change and save
		oneEmployee.setEname(givenEmpName);
		employeeDao.save(oneEmployee);
		
		// read it and test it 
		Optional<Employee> updatedEmployee = employeeDao.findById(givenEmpNo);
		assertTrue(updatedEmployee.isPresent());
		String expectedEmpNameAfterUpdate = givenEmpName;
		String actualEmpNameUpdated = updatedEmployee.get().getEname();
		assertEquals(expectedEmpNameAfterUpdate , actualEmpNameUpdated, failingMessage);
		System.out.println("done");
	}

}
