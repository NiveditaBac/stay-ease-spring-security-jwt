package com.springsecurity.stay_ease_jwt.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne 
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(nullable = false)
    private LocalDateTime bookingTime = LocalDateTime.now();

}
