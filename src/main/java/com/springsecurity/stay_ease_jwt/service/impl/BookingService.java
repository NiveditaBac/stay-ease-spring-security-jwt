package com.springsecurity.stay_ease_jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.stay_ease_jwt.dto.BookingDto;
import com.springsecurity.stay_ease_jwt.dto.UserDto;
import com.springsecurity.stay_ease_jwt.entity.Booking;
import com.springsecurity.stay_ease_jwt.entity.Hotel;
import com.springsecurity.stay_ease_jwt.exception.NotAvailableException;
import com.springsecurity.stay_ease_jwt.exception.NotFoundException;
import com.springsecurity.stay_ease_jwt.mapper.BookingMapper;
import com.springsecurity.stay_ease_jwt.mapper.UserMapper;
import com.springsecurity.stay_ease_jwt.repository.IBookingRepository;
import com.springsecurity.stay_ease_jwt.repository.IHotelRepository;
import com.springsecurity.stay_ease_jwt.service.IBookingService;

@Service
public class BookingService implements IBookingService{

    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private IHotelRepository hotelRepository;

    @Override
    public BookingDto bookRoom(Long hotelId, UserDto userDto) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new NotFoundException("Hotel not found"));

        if (hotel.getAvailableRooms() <= 0) {
            throw new NotAvailableException("No rooms available");
        }

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        Booking booking = new Booking();
        booking.setHotel(hotel);
        booking.setUser(UserMapper.mapToUser(userDto));
        return BookingMapper.mapToBookingDto(bookingRepository.save(booking));
    }

    @Override
    public void cancelRoom(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new NotFoundException("Booking not found"));
        Hotel hotel = booking.getHotel();
        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
        hotelRepository.save(hotel);
        bookingRepository.deleteById(bookingId);
    }
}
