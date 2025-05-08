package com.hotel.repository;

import com.hotel.model.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("reviewRepository")
public interface ReviewRepository extends JpaRepository<Review,Integer>{

}
