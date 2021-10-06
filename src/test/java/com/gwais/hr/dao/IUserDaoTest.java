package com.gwais.hr.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

//import org.hibernate.exception.GenericJDBCException;
//import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.gwais.hr.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class IUserDaoTest {
	
	@Autowired
	IUserDao userDao;
	

	@Test
	void testAutowired() {
		assertNotNull(userDao);
	}

	@Test
	@Transactional
	void testFindAll() {
		List<User> allUsers = userDao.findAll();
		assertNotNull(allUsers);
		assertTrue(allUsers.size() > 0);
	}

	@Test
	@Transactional
	void testFindAllById() {
		Long userID = 1l;
		Optional<User> returnedUser = userDao.findById(userID);
		// should be there
		assertTrue(returnedUser.isPresent(), "User is not found");
		assertTrue(returnedUser.get().getUsername().equalsIgnoreCase("zoalsai"));
	}

	@Test
	@Transactional
	void testFindAllById_NoResult() {
		Long userID = 33333l;
		Optional<User> returnedUser = userDao.findById(userID);
		// shouldn't be there
		assertFalse(returnedUser.isPresent(), "User is not supposed to be found");
	}

	@Test
	@Transactional
	void testGetOne() {
		Long userID = 1l;
		User oneUser = userDao.getOne(userID);
		assertNotNull(oneUser);
		assertTrue(oneUser.getUsername().equalsIgnoreCase("zoalsai"));
	}

	@Test
	@Transactional
	void testGetByUsername() {
		String userName = "zoalsai";
		User oneUser = userDao.findByUsername(userName);
		assertNotNull(oneUser);
		assertTrue(oneUser.getUsername().equalsIgnoreCase(userName));
	}
	
	@Test
    @DisplayName("Test save and deletById")
	@Transactional
    void testSaveAndDeleteById() {
        String username = "al3awad";
        String password = "3oook";
        
        User someUser = new User();
		someUser.setUsername(username);
		someUser.setPassword(password);
        
        User returnedUser = userDao.save(someUser);
        // Assert the response
        assertNotNull(returnedUser, "The saved User should not be null");
        assertTrue(returnedUser.getUserId() > 1, "The user ID should be more than 1");
        assertEquals(username, returnedUser.getUsername(), "The usernane should be identical");
        
        userDao.delete(returnedUser);
        
        Optional<User> deleteddUser = userDao.findById(returnedUser.getUserId());
		// shouldn't be there
		assertFalse(deleteddUser.isPresent(), "User is not supposed to be found");
    }
	
	/*
	@Ignore
	@Test
	@Rollback(false)
	@Transactional
    void testSaveAndDeleteById_moreTha() {
        String username = "al3awad123al3awad123al3awad123"; 
        String password = "3oook";
        
        User someUser = new User();
		someUser.setUsername(username);
		someUser.setPassword(password);

		//User xxxx = userDao.save(someUser);
		/*
		 * JpaSystemException GenericJDBCException StandardSQLExceptionConverter
		 * SqlExceptionHelper
		 * /
		assertThrows(GenericJDBCException.class, () -> { userDao.save(someUser);});
    }
*/
}
