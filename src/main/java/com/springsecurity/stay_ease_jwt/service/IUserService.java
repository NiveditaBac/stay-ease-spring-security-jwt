package com.springsecurity.stay_ease_jwt.service;

import com.springsecurity.stay_ease_jwt.dto.UserDto;
import com.springsecurity.stay_ease_jwt.dto.LoginResponse;


public interface IUserService {

    UserDto registerUser(UserDto userDto);
    public UserDto getUserByEmail(String email);
    public LoginResponse login();

}
