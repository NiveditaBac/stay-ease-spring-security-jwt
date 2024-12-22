package com.springsecurity.stay_ease_jwt.mapper;

import com.springsecurity.stay_ease_jwt.entity.User;
import com.springsecurity.stay_ease_jwt.dto.UserDto;

public class UserMapper {

    public static User mapToUser(UserDto userDto){
        User user = new User(
            userDto.getUserId(), 
            userDto.getEmail(), 
            userDto.getPassword(), 
            userDto.getFirstName(), 
            userDto.getLastName(), 
            userDto.getRole()
        );
        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
            user.getUserId(),
            user.getEmail(),
            user.getPassword(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole()
        );
        return userDto;
    }

}
