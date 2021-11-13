package com.gwais.hr.service;

import com.gwais.hr.dto.ApiResponse;
import com.gwais.hr.dto.LoginDto;
import com.gwais.hr.dto.SignUpDto;

public interface UserService {

	ApiResponse signUp(SignUpDto signUpDto);
	ApiResponse login(LoginDto loginDto);

}
