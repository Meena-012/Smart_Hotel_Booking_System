package com.hotel.dto;

import com.hotel.model.Hotels;
import com.hotel.dto.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelRoomResponseDTO {
	
	private Hotels hotel;
	private Room room;

}
