package com.springsecurity.stay_ease_jwt.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.springsecurity.stay_ease_jwt.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userId;
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "first name cannot be blank")
    private String firstName;
    @NotBlank(message = "last name cannot be blank")
    private String lastName;
    @JsonEnumDefaultValue
    private Role role = Role.CUSTOMER;
}
