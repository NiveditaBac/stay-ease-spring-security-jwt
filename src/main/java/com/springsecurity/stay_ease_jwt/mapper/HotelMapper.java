package com.springsecurity.stay_ease_jwt.mapper;

import com.springsecurity.stay_ease_jwt.dto.HotelDto;
import com.springsecurity.stay_ease_jwt.entity.Hotel;

public class HotelMapper {

    public static Hotel mapToHotel(HotelDto hotelDto){
        Hotel hotel = new Hotel(
            hotelDto.getHotelId(),
            hotelDto.getHotelName(),
            hotelDto.getDescription(),
            hotelDto.getAvailableRooms());
           
        return hotel;
    }

    public static HotelDto mapToHotelDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto(
            hotel.getHotelId(),
            hotel.getHotelName(),
            hotel.getDescription(),
            hotel.getAvailableRooms()
        );
        return hotelDto;
    }

}
