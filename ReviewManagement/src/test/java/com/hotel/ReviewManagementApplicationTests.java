package com.hotel;

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

@SpringBootTest
class ReviewManagementApplicationTests {

	@Mock
	ReviewRepository repository;

	@Mock
	UserClient userClient;

	@InjectMocks
	ReviewServiceImpl service;

	@Test
	void reviewTest() {
		LocalDateTime now = LocalDateTime.now();
		Review review = new Review(1, 101, 202, "Great service!", 5, now);
		Mockito.when(repository.save(review)).thenReturn(review);
		String response = service.addReview(review);
		assertEquals("Review Added Successfully!!!", response);
	}

	@Test
	void updateReviewTest() {
		LocalDateTime now = LocalDateTime.now();
		Review review = new Review(1, 101, 202, "Great Experience!", 5, now);
		Mockito.when(repository.save(review)).thenReturn(review);
		String response = service.updateReview(review);
		assertEquals("Review Information Updated Successfully!!", response);
	}

	@Test
	void deleteReviewTest() {
		int id = 1;
		Mockito.doNothing().when(repository).deleteById(id);
		String response = service.deleteReview(id);
		assertEquals("Review Deleted Successfully", response);
	}

	@Test
	void getReviewByIdTest() throws ReviewNotFound, UserNotFound {
		LocalDateTime now = LocalDateTime.now();
		Review review = new Review(101, 1, 202, "Great service!", 5, now);
		UserRole user = new UserRole(1, "Meena", "Meena@gmail.com", "meena", "admin");
		Mockito.when(repository.findById(101)).thenReturn(Optional.of(review));
		Mockito.when(userClient.getUser(1)).thenReturn(user);
		ReviewUserResponseDTO response = service.getReviewById(101);
		assertEquals(new ReviewUserResponseDTO(review, user), response);
	}

//	@Test
//	void reviewNotFoundTest() {
//
//	}
//
//	@Test
//	void userNotFoundTest() {
//
//	}

	@Test
	void getAllReviewTest() {
		LocalDateTime now = LocalDateTime.now();
		Review reviewOne = new Review(101, 1, 202, "Great service!", 5, now);
		Review reviewTwo = new Review(102, 2, 203, "Peacefull Stay", 3, now);
		Review reviewThree = new Review(103, 3, 203, "Very Good Experience", 4, now);
		List<Review> reviews = new ArrayList<>();
		reviews.add(reviewOne);
		reviews.add(reviewTwo);
		reviews.add(reviewThree);
		Mockito.when(repository.findAll()).thenReturn(reviews);
		List<Review> response = service.getAllReviews();
		assertEquals(reviews, response);
	}

}
