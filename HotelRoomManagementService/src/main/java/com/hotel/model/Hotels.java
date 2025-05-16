package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "hotel_info")
@Data // Lombok annotation to automatically generate getters, setters,equals,hashCode, and toString methods.
@RequiredArgsConstructor
public class Hotels {

	// Marks this field as the primary key
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq")
	@SequenceGenerator(name = "hotel_seq", sequenceName = "hotel_sequence", initialValue = 101, allocationSize = 1)
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

	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
	// @SequenceGenerator(name = "room_seq", sequenceName = "room_sequence",
	// initialValue = 201, allocationSize = 1)
	private int roomId;

	private String amenities;

	private int roomCount;

	public Hotels(String hotelName, String location, int managerId, int roomId, String amenities, int roomCount) {
		super();
		this.hotelName = hotelName;
		this.location = location;
		this.managerId = managerId;
		this.roomId = roomId;
		this.amenities = amenities;
		this.roomCount = roomCount;
	}

//    @Min(value = 1, message = "Rating must be at least 1")
//    @Max(value = 5, message = "Rating must not exceed 5")
//    private int rating;

}
