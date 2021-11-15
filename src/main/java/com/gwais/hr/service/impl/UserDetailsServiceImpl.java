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
   
}
