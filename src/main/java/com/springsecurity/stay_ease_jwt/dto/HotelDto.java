package com.springsecurity.stay_ease_jwt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    private Long hotelId; 
    @NotBlank(message = "hotel name cannot be blank")
    private String hotelName;
    @NotBlank(message = "hotel description cannot be blank")
    private String description;
    @NotBlank(message = "available rooms cannot be blank")
    private int availableRooms;

}
