package com.hotel.dto;

import com.hotel.model.Hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelRoomRequestDTO {
	
	private Hotels hotel;
	private Room room;

}
