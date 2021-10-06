package com.gwais.hr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gwais.hr.model.User;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {

	// methods are implicit
	User findByUsername(Object userName);
}
