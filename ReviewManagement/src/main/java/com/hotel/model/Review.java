package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	@Id
	private int reviewId;
	private int userId;
	private int hotelId;
	private String comment;
	private int rating;
	private long timestamp = System.currentTimeMillis() / 1000L; 
}
