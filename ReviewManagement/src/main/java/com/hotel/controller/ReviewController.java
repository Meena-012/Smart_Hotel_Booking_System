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

import com.hotel.exception.ReviewNotFound;
import com.hotel.model.Review;
import com.hotel.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService service;
    
    @PostMapping("/saveReview")
    public String addReview(@RequestBody Review review) {
        return service.addReview(review);
    }

    @PutMapping("/updateReview")
    public String updateReview(@RequestBody Review review) {
        return service.updateReview(review);
    }

    @DeleteMapping("/deleteReview/{rid}")
    public String deleteReview(@PathVariable("rid") int reviewId) {
        return service.deleteReview(reviewId);
    }

    @GetMapping("/fetchById/{rid}")
    public Review getReviewById(@PathVariable("rid") int reviewId) throws ReviewNotFound {
        return service.getReviewById(reviewId);
    }

    @GetMapping("/fetchAll")
    public List<Review> getAllReviews() {
        return service.getAllReviews();
    }
}
