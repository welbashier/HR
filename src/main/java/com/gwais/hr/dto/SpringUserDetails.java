package com.gwais.hr.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gwais.hr.model.HrUser;

// Wrapper Class
public class SpringUserDetails implements UserDetails{
	private static final long serialVersionUID = -431824108955454872L;
	
	HrUser databaseUser;
	
	String fullName;
	
	public SpringUserDetails(HrUser passedInUser) {
		this.databaseUser = passedInUser;
		this.fullName = passedInUser.getFirstName() + " " + passedInUser.getLastName();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = databaseUser.getRole();
		List<SimpleGrantedAuthority> authoroties = new ArrayList<>();
		
		// we have only one role, we are going to use as authority
		authoroties.add(new SimpleGrantedAuthority("ROLE_" + role));
		
		return authoroties;
	}

	@Override
	public String getPassword() {
		return databaseUser.getPassword();
	}

	@Override
	public String getUsername() {
		return databaseUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // isActive?
		return databaseUser.getActive().equals("1");
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return databaseUser.getActive().equals("1");
	}
	
	/*
	 * Additional customized methods
	 */

	public String getFirstName() {
		return databaseUser.getFirstName();
	}
	
	public String getLastName() {
		return databaseUser.getLastName();
	}
	
	public String getFullName() {
		return this.fullName;
	}
}
