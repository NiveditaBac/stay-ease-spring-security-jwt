package com.springsecurity.stay_ease_jwt.mapper;

import com.springsecurity.stay_ease_jwt.dto.BookingDto;
import com.springsecurity.stay_ease_jwt.entity.Booking;

public class BookingMapper {

     public static Booking mapToBooking(BookingDto bookingDto){
        Booking booking = new Booking(
            bookingDto.getBookingId(),
            bookingDto.getUser(),
            bookingDto.getHotel(),
            bookingDto.getBookingTime()
        );
        return booking;
    }

    public static BookingDto mapToBookingDto(Booking booking){

        BookingDto bookingDto = new BookingDto(
            booking.getBookingId(),
            booking.getUser(),
            booking.getHotel(),
            booking.getBookingTime()
        );
        return bookingDto;
    }
}