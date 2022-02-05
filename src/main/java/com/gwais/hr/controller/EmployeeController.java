package com.gwais.hr.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gwais.hr.dto.EmployeeDto;
import com.gwais.hr.service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// accessed by: http://localhost:8011/Employee/Status
	@GetMapping("/Status")
	public String status() {
		return "Up";
	}
	
	
	/*
	 * Returning JSON objects
	 */
	
	// getting all employees by: http://localhost:8011/Employee
	@GetMapping("")
	@Secured("ROLE_ADMIN") /* limited to users with ADMIN role.  must be added */
	public List<EmployeeDto> listAllEmployees() {
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return employees;
	}

	// accessed by: http://localhost:8011/Employee/7566
	@GetMapping("/{id}")
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public ResponseEntity<?> getEmployee(@PathVariable Long id, Principal principal, HttpServletRequest request) {
		EmployeeDto readEmployee = employeeService.getOneEmployee(id);
		
		if (request.isUserInRole("ROLE_ADMIN")
				|| (request.isUserInRole("ROLE_USER") 
						&& principal.getName().equalsIgnoreCase(readEmployee.getEname())
						)
				){
			return new ResponseEntity<>(readEmployee, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("ERROR", HttpStatus.UNAUTHORIZED);
	}

	// accessed by API client (e.g. Postman) to post/add employees
	@PostMapping("/")
	@Secured("ROLE_ADMIN") /* if global method security is enabled, only admins can execute */
	public EmployeeDto postEmployee(@RequestBody EmployeeDto changedEmployee) {
		EmployeeDto addedEmployee = employeeService.addEmployee(changedEmployee);
		return addedEmployee;
	}

	@PostMapping("/Register")
	@Secured("ROLE_ADMIN")
	public EmployeeDto registerEmployee(@RequestBody EmployeeDto newEmployee) {
		EmployeeDto changedEmployee = new EmployeeDto();
		EmployeeDto addedEmployee = employeeService.addEmployee(changedEmployee );
		return addedEmployee;
	}

	// accessed by API client (e.g. Postman) to put/update employees
	@PutMapping("/{id}") // may not be needed
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public EmployeeDto putEmployee(
			@RequestBody EmployeeDto changedEmployee,
			@PathVariable(name = "id", required = true) Long id) {
		return employeeService.modifyEmployee(changedEmployee, id);
	}

	// accessed by API client (e.g. Postman) to delete employees
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public String deleteEmployee(@PathVariable Long id) {
		return employeeService.removeEmployee(id);
	}
	
	
	/*
	 * Returning static HTML pages/views
	 */
	
	@GetMapping(value = { "/home"})
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeeHome");
		return model;
	}
	
	@GetMapping(value = { "/profile"})
	public ModelAndView profile() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeeProfile");
		return model;
	}
	
	@GetMapping(value = { "/timeCard"})
	public ModelAndView timeCard() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeeTimeCard");
		return model;
	}
	
	@GetMapping(value = { "/benefits"})
	public ModelAndView benefits() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeeBenefits");
		return model;
	}
	
	@GetMapping(value = { "/training"})
	public ModelAndView training() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeeTraining");
		return model;
	}
	
	@GetMapping(value = { "/payments"})
	public ModelAndView payments() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeePayments");
		return model;
	}
	
	/*
	 * Administration pages
	 */
	
	@GetMapping(value = { "/admin"})
	@Secured("ROLE_ADMIN")
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("employeeAdmin");
		return model;
	}
	
	@GetMapping(value = { "/admin/searchEmployee"})
	@Secured("ROLE_ADMIN")
	public ModelAndView adminEmployeeSearch() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/administration/searchEmployee");
		return model;
	}
	
	@GetMapping(value = { "/admin/addEmployee"})
	@Secured("ROLE_ADMIN")
	public ModelAndView adminEmployeeAdd() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/administration/addEmployee");
		return model;
	}
	
	@GetMapping(value = { "/admin/modifyEmployee"})
	@Secured("ROLE_ADMIN")
	public ModelAndView adminEmployeeModify() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/administration/modifyEmployee");
		return model;
	}
	
	@GetMapping(value = { "/admin/manageBenefits"})
	@Secured("ROLE_ADMIN")
	public ModelAndView adminEmployeeBenefits() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/administration/manageBenefits");
		return model;
	}
	
	@GetMapping(value = { "/admin/manageAnnouncements"})
	@Secured("ROLE_ADMIN")
	public ModelAndView adminAnnouncements() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/administration/manageAnnouncements");
		return model;
	}
}
