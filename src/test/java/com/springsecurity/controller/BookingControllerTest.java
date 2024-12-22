package com.springsecurity.controller;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.stay_ease_jwt.controller.BookingController;
import com.springsecurity.stay_ease_jwt.dto.BookingDto;
import com.springsecurity.stay_ease_jwt.dto.HotelDto;
import com.springsecurity.stay_ease_jwt.dto.UserDto;
import com.springsecurity.stay_ease_jwt.entity.Role;
import com.springsecurity.stay_ease_jwt.mapper.HotelMapper;
import com.springsecurity.stay_ease_jwt.mapper.UserMapper;
import com.springsecurity.stay_ease_jwt.service.IBookingService;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IBookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private BookingDto bookingDto;
    private UserDto userDto;
    private HotelDto hotelDto;

    @BeforeEach
    void setUp(){

        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();

        userDto = new UserDto(1L, "john.david@example.com", "password" , "John","David" , Role.ADMIN);
        hotelDto = new HotelDto(1L, "Holiday Inn", "5 star", 5);
        bookingDto = new BookingDto(1L, UserMapper.mapToUser(userDto), HotelMapper.mapToHotel(hotelDto), LocalDateTime.now());
    }

    private static final String BASE_URL = BookingController.BOOKING_API_ENDPOINT;
    public final static String BOOKING_URL = BookingController.BOOK_ROOM_ENDPOINT;
    public final static String CANCEL_URL = BookingController.CANCEL_ROOM_ENDPOINT;

    @Test
    @DisplayName("Room Bookimg for given user and hotel")
    public void testRoomBook_ReturnsBookingDto_WhenRoomIsBooked() throws Exception {
        Long hotelId = 1L;
        Mockito.when(bookingService.bookRoom(hotelId, userDto)).thenReturn(bookingDto);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + BOOKING_URL, hotelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
               .andExpect(MockMvcResultMatchers.status().isAccepted())
               .andExpect(MockMvcResultMatchers.jsonPath("$.bookingId", Matchers.is(1)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.user", Matchers.is(userDto)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.hotel", Matchers.is(hotelDto)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.bookingTime", Matchers.notNullValue()));
    }

}