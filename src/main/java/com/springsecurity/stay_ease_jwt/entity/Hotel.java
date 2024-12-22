package com.springsecurity.stay_ease_jwt.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId; 

    @Column(nullable = false)
    private String hotelName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int availableRooms;

}
