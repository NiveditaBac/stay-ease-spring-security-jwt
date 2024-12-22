package com.springsecurity.controller;

import static org.mockito.ArgumentMatchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springsecurity.stay_ease_jwt.controller.UserController;
import com.springsecurity.stay_ease_jwt.dto.UserDto;
import com.springsecurity.stay_ease_jwt.entity.Role;
import com.springsecurity.stay_ease_jwt.service.IUserService;

import org.junit.jupiter.api.Test;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IUserService userService;

    private UserDto userDto;

    private static final String BASE_URL = UserController.USER_API_ENDPOINT;
    private static final String REGISTER_URL = UserController.USER_REGISTER_API;

    @BeforeEach
    void setUp(){
        userDto = new UserDto(1L, "john.david@example.com", "password" , "John","David" , Role.ADMIN);
    }

    @Test
    @DisplayName("Should return user after successfull registration")
    void testRegisterUser_Success() throws Exception {

        Mockito.when(userService.registerUser(any(UserDto.class)))
                .thenReturn(userDto);

        String requestBody = """
                                {
                                    "firstName": "John",
                                    "lastName": "David",
                                    "email": "john.david@example.com",
                                    "password": "password",
                                    "role": "ADMIN"
                                }
                                """;

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + REGISTER_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("John")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("David")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("john.david@example.com")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("ADMIN")));

    }

}