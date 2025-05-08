package com.hotel.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "booking_info")
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

	@Id
	@Positive(message = "Booking ID must be a positive number")
	private int bookingid;

	@Positive(message = "User ID must be a positive number")
	private int userId;

	@Positive(message = "Room ID must be a positive number")
	private int roomId;

	private int hotelId;
	
	@NotNull(message = "Check-in date cannot be null")
	@FutureOrPresent(message = "Check-in date must be today or in the future")
	private LocalDate checkInDate;

	@NotNull(message = "Check-out date cannot be null")
	@Future(message = "Check-out date must be in the future")
	private LocalDate checkOutDate;
	
	private String status;

}
