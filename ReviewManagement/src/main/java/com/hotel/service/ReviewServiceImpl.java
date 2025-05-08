package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.exception.ReviewNotFound;
import com.hotel.model.Review;
import com.hotel.repository.ReviewRepository;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    ReviewRepository repository;

    @Override
    public String addReview(Review review) {
        Review savedReview = repository.save(review);
        if (savedReview != null) {
            log.info("New Review is Added");
            return "Review Added Successfully!!!";
        }
        else {
        	return "Something Went wrong with Adding Review";
        }
    }

    @Override
    public String updateReview(Review review) {
        log.info("In ReviewServiceImpl updateReview method...");
        Review updatedReview = repository.save(review);
        if (updatedReview != null) {
            return "Review Information Updated Successfully!";
        } else {
            return "Something Went Wrong with Review Update";
        }
    }

    @Override
    public String deleteReview(int reviewId) {
        log.info("In ReviewServiceImpl deleteReview method...");
        repository.deleteById(reviewId);
        return "Review Deleted Successfully";
    }

    @Override
    public Review getReviewById(int id) throws ReviewNotFound {
        Optional<Review> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ReviewNotFound("Review ID is invalid");
        }
    }

    @Override
    public List<Review> getAllReviews() {
        log.info("In ReviewServiceImpl getAllReviews method...");
        return repository.findAll();
    }
}

