package com.hotel.dto;

import com.hotel.model.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUserResponseDTO {
	private Review review;
	private UserRole user;

}
