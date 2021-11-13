package com.gwais.hr.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwais.hr.dao.IUserDao;
import com.gwais.hr.dto.ApiResponse;
import com.gwais.hr.dto.LoginDto;
import com.gwais.hr.dto.SignUpDto;
import com.gwais.hr.model.User;
import com.gwais.hr.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserDao userDao;


    @Override
    public ApiResponse signUp(SignUpDto signUpDto) {
        validateSignUp(signUpDto);
        User user = new User();
        //can use Bcrypt
        BeanUtils.copyProperties(signUpDto, user);
        userDao.save(user);
        return new ApiResponse(200, "success", user);
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {
        User user = userDao.findByUsername(loginDto.getUsername());
        if(user == null) {
            throw new RuntimeException("User does not exist.");
        }
        if(!user.getPassword().equals(loginDto.getPassword())){
            throw new RuntimeException("Password mismatch.");
        }
        return new ApiResponse(200, "Login success", null) ;

    }

    private void validateSignUp(SignUpDto signUpDto) {
    	// add logic to make sure all required fields are filled
    }
}
