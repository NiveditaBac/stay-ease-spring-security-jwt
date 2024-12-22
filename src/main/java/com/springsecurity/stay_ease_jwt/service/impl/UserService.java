package com.springsecurity.stay_ease_jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.stay_ease_jwt.dto.LoginResponse;
import com.springsecurity.stay_ease_jwt.dto.UserDto;
import com.springsecurity.stay_ease_jwt.entity.User;
import com.springsecurity.stay_ease_jwt.exception.NotFoundException;
import com.springsecurity.stay_ease_jwt.mapper.UserMapper;
import com.springsecurity.stay_ease_jwt.repository.IUserRepository;
import com.springsecurity.stay_ease_jwt.service.IUserService;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                                  .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public LoginResponse login() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException("User not found with Email ID : " + email));
        return new LoginResponse("User successfully logged in", user);
    }
}