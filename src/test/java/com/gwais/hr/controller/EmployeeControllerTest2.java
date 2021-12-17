package com.gwais.hr.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gwais.hr.service.EmployeeService;
import com.gwais.hr.service.impl.EmployeeServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EmployeeController.class)
@WebAppConfiguration
public class EmployeeControllerTest2 {

	@Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    
    @Autowired
    EmployeeServiceImpl employeeService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
    

    @Test
    @WithMockUser(username="welbashier",password="willi123") //,roles={"USER"})
    public void asdasdasd() throws Exception {
		/*
		 * mvc.perform(get("/Employee/Status").contentType(MediaType.ALL))
		 * .andExpect(status().isOk());
		 */
        
        mvc.perform(get("/Employee/Status"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("Item 1"))
        .andExpect(jsonPath("$[0].ownerName").exists());
    }
    
    
	/*
	 * @Test public void givenAuthRequestOnControllerStatus_shouldSucceedWith200()
	 * throws Exception { ResponseEntity<String> result = template
	 * .withBasicAuth("welbashier", "willi123") .getForEntity("/Employee/Status",
	 * String.class);
	 * 
	 * assertEquals(HttpStatus.OK, result.getStatusCode()); assertEquals("Up",
	 * result.getBody()); }
	 

    @Test
    public void givenAdminUserOnControllerWithID_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template
        				.withBasicAuth("manager", "go2manage") // this is an admin
        				.getForEntity("/Employee/3", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }*/
    
    
    // TODO: test the rest of the methods

}
