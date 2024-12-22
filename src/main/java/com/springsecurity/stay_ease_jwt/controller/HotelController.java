package com.springsecurity.stay_ease_jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.stay_ease_jwt.dto.HotelDto;
import com.springsecurity.stay_ease_jwt.service.IHotelService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(HotelController.HOTEL_API_ENDPOINT)
@Log4j2
public class HotelController {

    public final static String HOTEL_API_ENDPOINT = "/api/hotels";
    public final static String HOTEL_ID_ENDPOINT = "/{hotelId}";

    @Autowired
    private IHotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> addHotel(@Valid @RequestBody HotelDto hotelDto) {
        log.info("Hotel add request called : " + hotelDto);
        HotelDto createdHotel = hotelService.addHotel(hotelDto);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @GetMapping(HOTEL_ID_ENDPOINT)
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId){
        log.info("Hotel get request for ID: " + hotelId);
        HotelDto hotelDto = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotelDto);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels(){
        log.info("Hotel get all request called");
        List<HotelDto> hotelDtos = hotelService.getAllAvailableHotels();
        if(hotelDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hotelDtos, HttpStatus.OK);
    }   

    @PutMapping(HOTEL_ID_ENDPOINT)
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long hotelId, @RequestBody HotelDto hotelDto){  
        log.info("Hotel update called for Hotel ID: " + hotelId);
        return ResponseEntity.ok(hotelService.updateHotel(hotelId, hotelDto));
    }   

    @DeleteMapping(HOTEL_ID_ENDPOINT)
    public ResponseEntity<String> deleteHotel(@PathVariable Long hotelId){
        log.info("Hotel delete called for Hotel ID: " + hotelId);
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok("Hotel deleted successfully");
    }


}
