package com.hotel.dto;

import java.util.List;

import com.hotel.model.Hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data Transfer Object for responding with hotel and a list of rooms.
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelRoomResponseDTO {
	private Hotels hotel; // Contains details of the hotel.
	private List<Room> room; // Contains a list of rooms in the hotel.

}