package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to automatically generate getters, setters, equals, hashCode, and toString methods.
@NoArgsConstructor // Lombok annotation to automatically generate a constructor with no arguments.
@AllArgsConstructor // Lombok annotation to automatically generate a constructor with all fields as arguments.
public class Room {

	private int roomId;
	private int hotelId;
	private int userId;
	private String type;
	private double price;
	private String features;
}
