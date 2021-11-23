package com.gwais.hr.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gwais.hr.dao.UserDao;
import com.gwais.hr.model.HrUser;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HrUser user = userDao.findByUsername(username);
		/*
		 * In the database we have:
		 * 
		 * User1: welbashier:willi123 = USER
		 * User2: manager:go2mange = ADMIN
		 * 
		 * you can use the bash shell to run:
		 * 		$ curl -u manager:go2manage localhost:8011/Employee/
		 * or
		 * 		$ curl -u manager:go2manage localhost:8011/Employee/3
		 */
		UserDetails springUserDetails = null;
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			springUserDetails = User
					.withUsername(user.getUsername())
					.password(user.getPassword())
					.authorities("ROLE_" + user.getRole()) 
					/* "ROLE_" needed to match with roles in Spring */
					.build();
		}
		
		return springUserDetails;
	}

	/* reserved for creating new users:
	 * 
import org.springframework.security.crypto.password.PasswordEncoder;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	 * public HrUser save(HrUser user) { HrUser newUser = new HrUser();
	 * newUser.setUsername(user.getUsername());
	 * newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	 * newUser.setRole(user.getRole());
	 * 
	 * return userDao.save(newUser); }
	 * 
	 * put this in the controller to get the new user:
	 * 
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}
	 */
   
}
