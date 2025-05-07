package com.hotel.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Booking {

	private int bookingid;
	private int userId;
	private int roomId;
	private int hotelId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String status;

}
