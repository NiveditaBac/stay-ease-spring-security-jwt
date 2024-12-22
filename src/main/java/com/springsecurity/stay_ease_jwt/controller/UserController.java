package com.springsecurity.stay_ease_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.stay_ease_jwt.dto.EmailRequestDto;
import com.springsecurity.stay_ease_jwt.dto.LoginResponse;
import com.springsecurity.stay_ease_jwt.dto.UserDto;
import com.springsecurity.stay_ease_jwt.service.IUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(UserController.USER_API_ENDPOINT)
@Log4j2
public class UserController {

    public final static String USER_API_ENDPOINT = "/api/users";
    public final static String USER_REGISTER_API = "/register";
    public final static String USER_EMAIL_API = "/email";
    public final static String USER_LOGIN_API = "/login";

    @Autowired  
    private IUserService userService;

    @PostMapping(value = USER_REGISTER_API,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        log.info("Register Api called for user: " + userDto.getFirstName());
        
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping(USER_EMAIL_API)
    public ResponseEntity<UserDto> getUserByEmail(@RequestBody EmailRequestDto emailRequest){
        log.info("Get user by email Api called for Email ID: " + emailRequest.getEmail());
        UserDto userDto = userService.getUserByEmail(emailRequest.getEmail());
        return ResponseEntity.ok(userDto);
    }

    @PostMapping(USER_LOGIN_API)
    public ResponseEntity<LoginResponse> loginUser(){
        log.info("Login request called for user");
        return new ResponseEntity<>(userService.login(), HttpStatus.ACCEPTED);
    }
}