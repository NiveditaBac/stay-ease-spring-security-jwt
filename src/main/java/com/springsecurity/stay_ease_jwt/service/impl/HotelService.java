package com.springsecurity.stay_ease_jwt.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.stay_ease_jwt.dto.HotelDto;
import com.springsecurity.stay_ease_jwt.entity.Hotel;
import com.springsecurity.stay_ease_jwt.exception.AlreadyExistException;
import com.springsecurity.stay_ease_jwt.exception.NotFoundException;
import com.springsecurity.stay_ease_jwt.mapper.HotelMapper;
import com.springsecurity.stay_ease_jwt.repository.IHotelRepository;
import com.springsecurity.stay_ease_jwt.service.IHotelService;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private IHotelRepository hotelRepository;

    @Override
    public List<HotelDto> getAllAvailableHotels() {

        List<Hotel> hotels = hotelRepository.findByAvailableRoomsGreaterThan(0);
        return hotels.stream().map(hotel -> HotelMapper.mapToHotelDto(hotel)).collect(Collectors.toList());

    }

    @Override
    public HotelDto getHotel(Long hotelId) {

        Hotel hotel = hotelRepository.findById(hotelId)
                        .orElseThrow(() -> new NotFoundException("Hotel not found.")); 
        return HotelMapper.mapToHotelDto(hotel);
        
    }

    @Override
    public HotelDto addHotel(HotelDto hotelDto) {

        if(hotelRepository.existByHotelName(hotelDto.getHotelName()))
            throw new AlreadyExistException("Hotel already exist with name : " + hotelDto.getHotelName());
        Hotel hotel = HotelMapper.mapToHotel(hotelDto);
        hotel.setHotelName(hotelDto.getHotelName());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setAvailableRooms(hotelDto.getAvailableRooms());
        return HotelMapper.mapToHotelDto(hotelRepository.save(hotel));
    }

    @Override
    public HotelDto updateHotel(Long hotelId, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(hotelId)
                    .orElseThrow(() -> new NotFoundException("Hotel not found.")); 
        hotel.setHotelName(hotelDto.getHotelName());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setAvailableRooms(hotelDto.getAvailableRooms());
        return HotelMapper.mapToHotelDto(hotelRepository.save(hotel));
    }

    @Override
    public void deleteHotel(Long hotelId) {
        hotelRepository.findById(hotelId)
                      .orElseThrow(() -> new NotFoundException("Hotel not found with Id : " + hotelId));
        hotelRepository.deleteById(hotelId);
    }
}