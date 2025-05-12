package com.hotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Booking;

@Repository("bookingRepository") // Indicates this interface is a Spring Data JPA repository
// Extends JpaRepository to provide CRUD operations for the Booking entity with Integer as the ID type

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	// Retrieves bookings based on their status
	public abstract List<Booking> findByStatus(String status);

	// Retrieves bookings with a check-in date after the given date
	public abstract List<Booking> findByCheckInDateAfter(LocalDate date);

	// Retrieves bookings with a check-out date before the given date
	public abstract List<Booking> findByCheckOutDateBefore(LocalDate date);

	// Retrieves bookings for a specific user ID and with a specific status
	public abstract List<Booking> findByUserIdAndStatus(int userId, String status);

}