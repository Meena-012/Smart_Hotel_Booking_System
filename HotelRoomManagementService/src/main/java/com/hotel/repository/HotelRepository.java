package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Hotels;

@Repository("hotelrepository")
public interface HotelRepository extends JpaRepository<Hotels, Integer> {
	// Retrieves hotels based on the provided location.
	public abstract List<Hotels> findByLocation(String location);

	// Retrieves hotels based on the provided hotel name.
	public abstract List<Hotels> findByHotelName(String hotelName);
}