package com.springsecurity.stay_ease_jwt.dto;

import java.time.LocalDateTime;

import com.springsecurity.stay_ease_jwt.entity.Hotel;
import com.springsecurity.stay_ease_jwt.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long bookingId;
    private User user;
    private Hotel hotel;
    private LocalDateTime bookingTime = LocalDateTime.now();
}
