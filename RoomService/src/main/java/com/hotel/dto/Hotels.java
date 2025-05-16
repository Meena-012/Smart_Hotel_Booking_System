package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to automatically generate getters, setters, equals, hashCode, and toString methods.
@NoArgsConstructor // Lombok annotation to automatically generate a constructor with no arguments.
@AllArgsConstructor // Lombok annotation to automatically generate a constructor with all fields as arguments.
public class Hotels {
	private int hotelId;
    private String hotelName;
    private String location;
    private int managerId;
    private int roomId;
    private String amenities;
    private int roomCount;

}
