package com.gwais.hr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USR")
public class HrUser {
	
	@Id
	@SequenceGenerator(name = "userSeqGen", sequenceName = "USR_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "userSeqGen", strategy = GenerationType.SEQUENCE)
	@Column(name = "USR_ID", updatable = false, nullable = false)
	private Long userId; // emp_seq_generator

	@Column(name = "USERNAME")
	private String username;

	@JsonIgnore
	@Column(name = "PASSWORD")
	private String password;

	@JsonIgnore
	@Column(name = "EMAIL")
	private String email;
	
	@JsonIgnore
	@Column(name = "ROLE")
	private String role;
	
	@JsonIgnore
	@Column(name = "ACTIVE")
	private String active;
	
	@JsonIgnore
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@JsonIgnore
	@Column(name = "LAST_NAME")
	private String lastName;
	
	//List<UserRole> userRoles; // role (e.g. Manager) = group (e.g. Managers)
	
	//List<UserRights> userRights;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
