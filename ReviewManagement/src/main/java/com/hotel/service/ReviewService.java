package com.hotel.service;

import java.util.List;

import com.hotel.dto.ReviewUserResponseDTO;
import com.hotel.exception.ReviewNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Review;

public interface ReviewService {

	public abstract String addReview(Review review);

	public abstract String updateReview(Review review);

	public abstract String deleteReview(int reviewId);

	public abstract ReviewUserResponseDTO getReviewById(int id) throws ReviewNotFound,UserNotFound;

	public abstract List<Review> getAllReviews();

}
