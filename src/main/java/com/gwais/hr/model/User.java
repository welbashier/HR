package com.gwais.hr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USR")
public class User {
	
	@Id
	@SequenceGenerator(name = "usrSeqGenerator", sequenceName = "usr_seq", allocationSize = 1)
	@GeneratedValue(generator = "ursSeqGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "USR_ID", updatable = false, nullable = false)
	private Long userId; // emp_seq_generator

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

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

}
