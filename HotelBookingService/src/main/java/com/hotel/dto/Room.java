package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

	private int roomId; 
	private int hotelId; 
	private int userId;
	private String type; 
	private double price; 
	private String features;

}