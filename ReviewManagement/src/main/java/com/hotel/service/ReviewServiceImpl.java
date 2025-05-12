package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dto.ReviewUserResponseDTO;
import com.hotel.dto.UserRole;
import com.hotel.exception.ReviewNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Review;
import com.hotel.openFeign.UserClient;
import com.hotel.repository.ReviewRepository;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

	Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

	@Autowired
	ReviewRepository repository; // Injecting ReviewRepository dependency

	@Autowired
	UserClient userClient; // Injecting UserClient dependency for user-related operations

	@Override
	public String addReview(Review review) { // Implementation to add a new review
		Review savedReview = repository.save(review); // Saving the review to the database
		if (savedReview != null) { // Checking if the review was saved successfully
			log.info("New Review is Added");
			return "Review Added Successfully!!!";
		} else {
			return "Something Went wrong with Adding Review";
		}
	}

	@Override
	public String updateReview(Review review) { // Implementation to update an existing review
		log.info("In ReviewServiceImpl updateReview method...");
		Review updatedReview = repository.save(review); // Saving the updated review to the database
		if (updatedReview != null) { // Checking if the review was updated successfully
			return "Review Information Updated Successfully!!";
		} else {
			return "Something Went Wrong with Review Update";
		}
	}

	@Override
	public String deleteReview(int reviewId) { // Implementation to delete a review by its ID
		log.info("In ReviewServiceImpl deleteReview method...");
		repository.deleteById(reviewId); // Deleting the review from the database
		return "Review Deleted Successfully";
	}

	@Override
	public ReviewUserResponseDTO getReviewById(int id) throws ReviewNotFound, UserNotFound { // Implementation to retrieve a review and associated user by ID
		Optional<Review> optionalReview = repository.findById(id); // Finding the review by ID
		if (!optionalReview.isPresent()) { // Checking if the review exists
			throw new ReviewNotFound("Review ID not present");
		}
		Review review = optionalReview.get(); // Getting the review
		int userId = review.getUserId(); // Extracting the user ID from the review
		UserRole user = userClient.getUser(userId); // Calling the user service to get user details
		if (user == null) { // Checking if the user exists
			throw new UserNotFound("User ID is not present");
		}
		else {
		log.info("Response Retrived Successfully");
		return new ReviewUserResponseDTO(review, user); // Returning the review and user information
		}
	}

	@Override
	public List<Review> getAllReviews() { // Implementation to retrieve all reviews
		log.info("In ReviewServiceImpl getAllReviews method...");
		return repository.findAll(); // Fetching all reviews from the database
	}
}