package com.hotel;

// Importing necessary classes and packages
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.dto.ReviewUserResponseDTO;
import com.hotel.dto.UserRole;
import com.hotel.exception.ReviewNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Review;
import com.hotel.openFeign.UserClient;
import com.hotel.repository.ReviewRepository;
import com.hotel.service.ReviewServiceImpl;

// Annotation indicating this is a Spring Boot test class
@SpringBootTest
class ReviewManagementApplicationTests {

    // Mocking ReviewRepository for unit testing
    @Mock
    ReviewRepository repository;

    // Mocking UserClient for external user service calls
    @Mock
    UserClient userClient;

    // Injecting mocks into ReviewServiceImpl for testing
    @InjectMocks
    ReviewServiceImpl service;

    // Test case for adding a review
    @Test
    void reviewTest() throws UserNotFound {
        LocalDateTime now = LocalDateTime.now();
        Review review = new Review(101, 202, "Great service!", 5, now);

        // Mocking repository.save() method behavior
        Mockito.when(repository.save(review)).thenReturn(review);

        // Calling service method and asserting expected response
        String response = service.addReview(review);
        assertEquals("Review Added Successfully!!!", response);
    }

    // Test case for updating a review
    @Test
    void updateReviewTest() {
        LocalDateTime now = LocalDateTime.now();
        Review review = new Review(101, 202, "Great Experience!", 5, now);

        // Mocking repository.save() method behavior
        Mockito.when(repository.save(review)).thenReturn(review);

        // Calling service method and asserting expected response
        String response = service.updateReview(review);
        assertEquals("Review Information Updated Successfully!!", response);
    }

    // Test case for deleting a review
    @Test
    void deleteReviewTest() {
        int id = 1;

        // Mocking repository.deleteById() method behavior
        Mockito.doNothing().when(repository).deleteById(id);

        // Calling service method and asserting expected response
        String response = service.deleteReview(id);
        assertEquals("Review Deleted Successfully", response);
    }

    // Test case for fetching a review by its ID
    @Test
    void getReviewByIdTest() throws ReviewNotFound, UserNotFound {
        LocalDateTime now = LocalDateTime.now();
        Review review = new Review(1, 202, "Great service!", 5, now);
        UserRole user = new UserRole(1, "Meena", "Meena@gmail.com", "meena", "admin");

        // Mocking repository and userClient behavior
        Mockito.when(repository.findById(101)).thenReturn(Optional.of(review));
        Mockito.when(userClient.getUser(1)).thenReturn(user);

        // Calling service method and asserting expected response
        ReviewUserResponseDTO response = service.getReviewById(101);
        assertEquals(new ReviewUserResponseDTO(review, user), response);
    }

    // Placeholder for test cases related to exceptions
//    @Test
//    void reviewNotFoundTest() {
//    }
//
//    @Test
//    void userNotFoundTest() {
//    }

    // Test case for fetching all reviews
    @Test
    void getAllReviewTest() {
        LocalDateTime now = LocalDateTime.now();
        Review reviewOne = new Review(1, 202, "Great service!", 5, now);
        Review reviewTwo = new Review(2, 203, "Peaceful Stay", 3, now);
        Review reviewThree = new Review( 3, 203, "Very Good Experience", 4, now);

        // Creating a list of mock review objects
        List<Review> reviews = new ArrayList<>();
        reviews.add(reviewOne);
        reviews.add(reviewTwo);
        reviews.add(reviewThree);

        // Mocking repository.findAll() method behavior
        Mockito.when(repository.findAll()).thenReturn(reviews);

        // Calling service method and asserting expected response
        List<Review> response = service.getAllReviews();
        assertEquals(reviews, response);
    }
}
