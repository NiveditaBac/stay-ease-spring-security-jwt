package com.springsecurity.stay_ease_jwt.service;

import java.util.List;
import com.springsecurity.stay_ease_jwt.dto.HotelDto;

public interface IHotelService {

    List<HotelDto> getAllAvailableHotels();
    HotelDto getHotel(Long hotelId);
    HotelDto addHotel(HotelDto hotelDto);
    HotelDto updateHotel(Long hotelId, HotelDto hotelDto);
    void deleteHotel(Long hotelId);

}