package com.springsecurity.stay_ease_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springsecurity.stay_ease_jwt.entity.Booking;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    
}
