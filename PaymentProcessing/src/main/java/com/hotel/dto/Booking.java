package com.hotel.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to automatically generate getters, setters, equals, hashCode, and toString methods.
@NoArgsConstructor // Lombok annotation to automatically generate a constructor with no arguments.
@AllArgsConstructor // Lombok annotation to automatically generate a constructor with all fields as arguments.
public class Booking {

	private int bookingid;
	private int userId;
	private int roomId;
	private int hotelId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String status;

}
