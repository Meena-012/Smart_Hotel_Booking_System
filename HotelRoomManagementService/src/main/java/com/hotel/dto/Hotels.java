package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotels {
	private int hotelId;
    private String hotelName;
    private String location;
    private int managerId;
    private int roomId;
    private String amenities;
    private int roomCount;
    private int rating;
}
