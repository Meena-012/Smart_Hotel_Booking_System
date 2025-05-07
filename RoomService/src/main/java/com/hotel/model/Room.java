package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
	@Positive(message = "Room ID must be a positive number")
	@NotNull
    private int roomId; // Unique identifier for the room

    @Positive(message = "Hotel ID must be a positive number")
    @NotNull
    private int hotelId; // The hotel this room belongs to (foreign key)

    private int userId;
    
    @NotBlank(message = "Room type cannot be empty")
    @Size(min = 4, max = 50, message = "Room type must be between 4 and 50 characters")
    private String type; // Room type (Single, Double, Suite, Deluxe)

    @Positive(message = "Price must be a positive number")
    private double price; // Price per night for the room

    private boolean availability; // Availability status (true = available, false = booked)

    @NotEmpty(message = "Features cannot be empty")
    @Size(min = 5, max = 300, message = "Features must be between 5 and 300 characters")
    private String features; // Additional features (AC, TV, Balcony, Sea View)
}


