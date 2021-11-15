package com.gwais.hr.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.gwais.hr.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
final public class IEmployeeDaoTest {

	@Autowired
	EmployeeDao employeeDao;

	@Test
	void testAutowiredDao() {
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
		assertEquals(expectedEmpNameAfterUpdate, actualEmpNameUpdated, failingMessage);
		System.out.println("done");
	}

	@Test
	void testSearchByEname() {
		String empNameKeyword = "kin%";
		int expectedCount = 1;

		List<Employee> allMatchingEmployees = employeeDao.findByEnameLikeIgnoreCase(empNameKeyword);
		assertNotNull(allMatchingEmployees);
		assertTrue(allMatchingEmployees.size() > 0);
		assertEquals(expectedCount, allMatchingEmployees.size());

	}

	@Test
	void testSearchByDeptno() {
		Long deptNo = 30l; // 30 = sales department
		int expectedCount = 8;

		List<Employee> allMatchingEmployees = employeeDao.findByDeptno(deptNo);
		assertNotNull(allMatchingEmployees);
		assertTrue(allMatchingEmployees.size() > 0);
		assertEquals(expectedCount, allMatchingEmployees.size());
	}

	@Test
	void testSearchByHiredate() {
		String dateString = "2012-02-06";
		Date sqlDate = Date.valueOf(dateString);
		int expectedCount = 5;

		List<Employee> allMatchingEmployees = employeeDao.findByHiredateGreaterThanEqual(sqlDate);
		assertNotNull(allMatchingEmployees);
		assertTrue(allMatchingEmployees.size() > 0);
		assertEquals(expectedCount, allMatchingEmployees.size());
	}

	@Test
	void testSearchByJobAndDeptno() {
		String job = "sales";
		Long deptno = 30l;
		int expectedCount = 2;

		List<Employee> allMatchingEmployees = employeeDao.findByJobLikeIgnoreCaseAndDeptno(job, deptno);
		assertNotNull(allMatchingEmployees);
		assertTrue(allMatchingEmployees.size() > 0);
		assertEquals(expectedCount, allMatchingEmployees.size());
	}
}
