package com.springsecurity.stay_ease_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.stay_ease_jwt.dto.BookingDto;
import com.springsecurity.stay_ease_jwt.dto.UserDto;
import com.springsecurity.stay_ease_jwt.service.IBookingService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(BookingController.BOOKING_API_ENDPOINT)
@Log4j2
public class BookingController {

    public final static String BOOKING_API_ENDPOINT = "/api/bookings";
    public final static String BOOK_ROOM_ENDPOINT = "/{hotelId}/book-room";
    public final static String CANCEL_ROOM_ENDPOINT = "/{bookingId}/cancel-room";

    @Autowired
    private IBookingService bookingService;


    @PostMapping(BOOK_ROOM_ENDPOINT)
    public ResponseEntity<BookingDto> bookRoom(@PathVariable Long hotelId, @RequestBody UserDto userDto){
        log.info("Room book endpoint called for hotel id: " + hotelId);
        return new ResponseEntity<>(bookingService.bookRoom(hotelId, userDto), HttpStatus.ACCEPTED);
    }

    @PostMapping(CANCEL_ROOM_ENDPOINT)
    public ResponseEntity<Void> cancelRoom(@PathVariable Long bookingId){
        log.info("Room cancel endpoint called for booking id: " + bookingId);
        bookingService.cancelRoom(bookingId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
