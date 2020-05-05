package com.gwais.hr;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeHrApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getStatus() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/Employee/Status").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Up")));
	}
	
	@Test
	void contextLoads() {
	}

	@Test
	public void getAllEmployees() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/Employee"))
				.andExpect(status().isOk())
				;
	}

	@Test
	public void getAllDepartments() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/Department"))
				.andExpect(status().isOk())
				;
	}

}
