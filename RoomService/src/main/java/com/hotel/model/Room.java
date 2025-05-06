package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_info")
public class Room {

	@Id
	private int id; // Unique identifier for the room
	private int hotelId; // The hotel this room belongs to (foreign key)-->101,102..
	private String type; // Room type (e.g., Single, Double, Suite, Deluxe)
	private double price; // Price per night for the room
	private boolean availability; // Availability status (true = available, false = booked)
	private String features; // Additional features (e.g., AC, TV, Balcony, Sea View)
}


