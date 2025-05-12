package com.hotel.dto;

import com.hotel.model.Hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data Transfer Object for requesting hotel and room information.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelRoomRequestDTO {

	private Hotels hotel; // Contains details of the hotel.
	private Room room; // Contains details of the room.

}