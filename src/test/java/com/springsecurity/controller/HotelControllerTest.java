package com.springsecurity.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springsecurity.stay_ease_jwt.controller.HotelController;
import com.springsecurity.stay_ease_jwt.dto.HotelDto;
import com.springsecurity.stay_ease_jwt.service.IHotelService;

import org.hamcrest.Matchers;


@WebMvcTest(HotelController.class)
@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IHotelService hotelService;

    private HotelDto hotelDto;

    private static final String BASE_URL = HotelController.HOTEL_API_ENDPOINT;    

    @BeforeEach
    void setUp(){
        hotelDto = new HotelDto();
        hotelDto.setHotelId(1L);
        hotelDto.setHotelName("Holiday Inn");
        hotelDto.setDescription("5 star");
        hotelDto.setAvailableRooms(5);
    }

    @Test
    @DisplayName("Get Hotel by given Id")
    public void testHotelById() throws Exception{
        
        Mockito.when(hotelService.getHotel(1L)).thenReturn(hotelDto);

        String requestBody = """
                                {
                                    "hotelName": "Holiday Inn",
                                    "description": "5 star",
                                    "availableRooms": 5
                                }
                                """;

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.hotelName", Matchers.is("Holiday Inn")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("5 star")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.availableRooms", Matchers.is(5)));

        Mockito.verify(hotelService, Mockito.times(1)).getHotel(1L);
    }
} 