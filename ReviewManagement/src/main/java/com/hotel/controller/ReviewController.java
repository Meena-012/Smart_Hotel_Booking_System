package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.ReviewUserResponseDTO;
import com.hotel.exception.ReviewNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Review;
import com.hotel.service.ReviewService;

import jakarta.validation.Valid;

@RestController // Indicates that this class is a REST controller, handling incoming web requests
@RequestMapping("/review") // Maps HTTP requests with the base path "/review" to this controller
public class ReviewController {

	// Automatically injects an instance of ReviewService
	@Autowired
	ReviewService service;

	// Handles adding a new review
	@PostMapping("/saveReview")
	public String addReview(@Valid @RequestBody Review review) {
		return service.addReview(review);
	}

	// Handles updating an existing review
	@PutMapping("/updateReview")
	public String updateReview(@Valid @RequestBody Review review) {
		return service.updateReview(review);
	}

	// Handles deleting a review by its ID
	@DeleteMapping("/deleteReview/{rid}")
	public String deleteReview(@PathVariable("rid") int reviewId) {
		return service.deleteReview(reviewId);
	}

	// Handles fetching a review by its ID
	@GetMapping("/fetchById/{rid}")
	public ReviewUserResponseDTO getReviewById(@PathVariable("rid") int reviewId) throws ReviewNotFound, UserNotFound {
		return service.getReviewById(reviewId);
	}

	// Handles fetching all reviews
	@GetMapping("/fetchAll")
	public List<Review> getAllReviews() {
		return service.getAllReviews();
	}
}
