package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_info")
@Data // Lombok annotation to automatically generate getters, setters, equals,hashCode, and toString methods.
@NoArgsConstructor // Lombok annotation to automatically generate a constructor with no arguments.
@AllArgsConstructor // Lombok annotation to automatically generate a constructor with all fields as arguments.
public class Hotels {
	// Marks this field as the primary key
	@Id
	// Ensures the hotel ID is a positive number
	@Positive(message = "Hotel ID must be a positive number")
	@NotNull
	private int hotelId;

	// Ensures the hotel name is not blank and within the specified size
	@NotBlank(message = "Hotel name cannot be empty")
	@Size(min = 2, max = 100, message = "Hotel name must be between 2 and 100 characters")
	private String hotelName;

	// Ensures the location is not blank
	@NotBlank(message = "Location cannot be empty")
	private String location;
	
	// Ensures the manager ID is a positive number
	@Positive(message = "Manager ID must be a positive number")
	// Ensures the manager ID is not null
	private int managerId;

	private int roomId;

	private String amenities;

	private int roomCount;

//    @Min(value = 1, message = "Rating must be at least 1")
//    @Max(value = 5, message = "Rating must not exceed 5")
//    private int rating;

}
