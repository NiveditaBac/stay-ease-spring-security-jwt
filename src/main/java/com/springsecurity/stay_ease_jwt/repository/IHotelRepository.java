package com.springsecurity.stay_ease_jwt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springsecurity.stay_ease_jwt.entity.Hotel;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByAvailableRoomsGreaterThan(int rooms);
    boolean existByHotelName(String hotelName);
        
}
