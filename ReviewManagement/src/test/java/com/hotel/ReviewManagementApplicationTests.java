package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.model.Review;
import com.hotel.repository.ReviewRepository;
import com.hotel.service.ReviewServiceImpl;

@SpringBootTest
class ReviewManagementApplicationTests {

    @Mock
    ReviewRepository repository;
    @InjectMocks
    ReviewServiceImpl service;

    @Test
    void reviewTest() {
    	LocalDateTime now = LocalDateTime.now();
        Review review = new Review(1, 101, 202, "Great service!", 5,now );
        Mockito.when(repository.save(review)).thenReturn(review);
        String response = service.addReview(review);
        assertEquals("Review Added Successfully!!!", response);
    }
}
