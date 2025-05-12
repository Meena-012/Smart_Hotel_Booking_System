package com.hotel.repository;

import com.hotel.model.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indicates this interface is a Spring Data JPA repository
@Repository("reviewRepository")
// Extends JpaRepository to provide CRUD operations for the Review entity with Integer as the ID type
public interface ReviewRepository extends JpaRepository<Review,Integer>{

}