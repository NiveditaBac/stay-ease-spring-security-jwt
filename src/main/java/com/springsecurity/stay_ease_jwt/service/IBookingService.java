package com.springsecurity.stay_ease_jwt.service;

import com.springsecurity.stay_ease_jwt.dto.BookingDto;
import com.springsecurity.stay_ease_jwt.dto.UserDto;

public interface IBookingService {

    BookingDto bookRoom(Long hotelId, UserDto userDto);
    void cancelRoom(Long bookingId);
}
