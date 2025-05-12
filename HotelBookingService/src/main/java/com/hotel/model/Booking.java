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

@Entity // Marks this class as a JPA entity
@Data // Generates getters, setters, equals, hashCode, and toString methods
@Table(name = "booking_info") // Specifies the database table name for this entity
@AllArgsConstructor // Generates a constructor with all fields as arguments
@NoArgsConstructor // Generates a constructor with no arguments
public class Booking {

	// Marks this field as the primary key
	@Id
	// Ensures the booking ID is a positive number
	@Positive(message = "Booking ID must be a positive number")
	private int bookingid;

	// Ensures the user ID is a positive number
	@Positive(message = "User ID must be a positive number")
	private int userId;

	// Ensures the room ID is a positive number
	@Positive(message = "Room ID must be a positive number")
	private int roomId;

	private int hotelId;

	// Ensures the check-in date is not null and is in the present or future
	@NotNull(message = "Check-in date cannot be null")
	@FutureOrPresent(message = "Check-in date must be today or in the future")
	private LocalDate checkInDate;

	// Ensures the check-out date is not null and is in the future
	@NotNull(message = "Check-out date cannot be null")
	@Future(message = "Check-out date must be in the future")
	private LocalDate checkOutDate;

	private String status;

}