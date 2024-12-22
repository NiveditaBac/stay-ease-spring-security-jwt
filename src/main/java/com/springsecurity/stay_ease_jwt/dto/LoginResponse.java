package com.springsecurity.stay_ease_jwt.dto;

import com.springsecurity.stay_ease_jwt.entity.User;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    String message;
    User userDetails;
    
}
