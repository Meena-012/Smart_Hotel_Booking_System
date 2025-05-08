package com.hotel.service;

import java.util.List;

import com.hotel.exception.ReviewNotFound;
import com.hotel.model.Review;

public interface ReviewService {

	public abstract String addReview(Review review);

	public abstract String updateReview(Review review);

	public abstract String deleteReview(int reviewId);

	public abstract Review getReviewById(int id) throws ReviewNotFound;

	public abstract List<Review> getAllReviews();

}
