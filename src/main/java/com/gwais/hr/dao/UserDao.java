package com.gwais.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gwais.hr.model.HrUser;

@Repository
public interface UserDao extends JpaRepository<HrUser, Long> {

	// methods are implicit
	HrUser findByUsername(String userName);
}
