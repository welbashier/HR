package com.gwais.hr.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate template;
    

    @Test
    public void givenAuthRequestOnControllerStatus_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template
        				.withBasicAuth("welbashier", "willi123")
        				.getForEntity("/Employee/Status", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Up", result.getBody());
    }
    

    @Test
    public void givenWrongUsernameOnControllerStatus_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template
        				.withBasicAuth("welbashierX", "willi123")
        				.getForEntity("/Employee/Status", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotEquals("Up", result.getBody());
    }
    

    @Test
    public void givenWrongPasswordOnControllerStatus_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template
        				.withBasicAuth("welbashier", "xyzxyz")
        				.getForEntity("/Employee/Status", String.class); // /Status can be accessed by anyone

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotEquals("Up", result.getBody());
    }
    

    @Test
    public void givenAdminUserOnControllerWithID_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template
        				.withBasicAuth("manager", "go2manage") // this is an admin
        				.getForEntity("/Employee/3", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
    

    @Test
    public void givenAnyUserOnControllerWithID_shouldReturnWith401() throws Exception {
        ResponseEntity<String> result = template
        				.withBasicAuth("welbashier", "willi123")
        				.getForEntity("/Employee/3", String.class); // this user cannot see employee #3

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("ERROR", result.getBody());
    }
    

    @Test
    public void givenAnyUserOnControllerWithHisID_shouldSucceedWith401() throws Exception {
        ResponseEntity<String> result = template
        				.withBasicAuth("welbashier", "willi123")
        				.getForEntity("/Employee/5", String.class); // this user can see his record

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
    
    // TODO: test the rest of the methods

}
