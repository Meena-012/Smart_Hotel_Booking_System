package com.hotel.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity // Marks this class as a JPA entity
@Data // Generates getters, setters, equals, hashCode, and toString methods
@Table(name = "review_info") // Specifies the database table name for this entity
@RequiredArgsConstructor
public class Review {
	
	// Marks this field as the primary key
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
	@SequenceGenerator(name = "review_seq", sequenceName = "reveiw_sequence", initialValue = 501, allocationSize = 1)
	private int reviewId;

	// Ensures the user ID is not null
	@NotNull(message = "User ID cannot be null")
	private int userId;

	// Ensures the hotel ID is not null
	@NotNull(message = "Hotel ID cannot be null")
	private int hotelId;

	// Ensures the comment is not blank and has a maximum size
	@NotBlank(message = "Comment cannot be blank")
	@Size(max = 50, message = "Comment cannot exceed 50 characters")
	private String comment;

	// Ensures the rating is within the valid range
	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating must be at most 5")
	private int rating;
	private LocalDateTime timestamp;

	// Automatically sets the timestamp before persisting the entity
	@PrePersist
	protected void onCreate() {
		this.timestamp = LocalDateTime.now();
	}

	public Review(int userId,int hotelId, String comment,int rating,LocalDateTime timestamp) {
		super();
		this.userId = userId;
		this.hotelId = hotelId;
		this.comment = comment;
		this.rating = rating;
		this.timestamp = timestamp;
	}
}
