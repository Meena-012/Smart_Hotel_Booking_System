package com.hotel.dto;

import com.hotel.model.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data Transfer Object for review and user information in a response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUserResponseDTO {
	private Review review; // Contains the review details
	private UserRole user; // Contains the user's role information

}