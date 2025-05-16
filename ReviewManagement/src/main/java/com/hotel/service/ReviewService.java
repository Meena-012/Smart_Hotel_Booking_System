package com.hotel.service;

import java.util.List;

import com.hotel.dto.ReviewUserResponseDTO;
import com.hotel.exception.ReviewNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Review;

public interface ReviewService {

	// Abstract method to add a new review
	public abstract String addReview(Review review) throws UserNotFound;

	// Abstract method to update an existing review
	public abstract String updateReview(Review review);

	// Abstract method to delete a review by its ID
	public abstract String deleteReview(int reviewId);

	// Abstract method to retrieve a review and associated user by ID
	public abstract ReviewUserResponseDTO getReviewById(int id) throws ReviewNotFound,UserNotFound;

	// Abstract method to retrieve all reviews
	public abstract List<Review> getAllReviews();

}