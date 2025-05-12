package com.hotel.controller;

import java.time.LocalDate;
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

import com.hotel.exception.BookingNotFound;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Booking;
import com.hotel.service.BookingService;

import jakarta.validation.Valid;

//REST controller for managing booking-related operations.
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService service; // Injecting the BookingService dependency.

	// Endpoint to add a new booking.
	@PostMapping("/savebooking")
	public String addBooking(@Valid @RequestBody Booking user) throws HotelNotFoundException, RoomNotFound {
		return service.addBooking(user);
	}

	// Endpoint to update an existing booking.
	@PutMapping("/updateBooking")
	public String updateBooking(@Valid @RequestBody Booking user) throws HotelNotFoundException, RoomNotFound {
		return service.updateBooking(user);
	}

	// Endpoint to delete a booking by its ID.
	@DeleteMapping("/deleteBooking/{did}")
	public String deleteBooking(@PathVariable("did") int userId) {
		return service.deleteBooking(userId);
	}

	// Endpoint to retrieve a booking by its ID.
	@GetMapping("/fetchById/{bid}")
	public Booking getBookingById(@PathVariable("bid") int userId) throws BookingNotFound {
		return service.getBookingById(userId);
	}

	// Endpoint to retrieve all bookings.
	@GetMapping("fetchAll")
	public List<Booking> getAllBooking() {
		return service.getAllBooking();
	}

	// Endpoint to retrieve bookings by their status
	@GetMapping("getByStatus/{status}")
	public List<Booking> findByStatus(@PathVariable("status") String status) {
		return service.findByStatus(status);
	}

	// Endpoint to retrieve bookings with a check-out date before a specific date.
	@GetMapping("/checkOutDateBefore/{date}")
	public List<Booking> getBookingsByCheckOutDateBefore(@PathVariable("date") LocalDate date) {
		return service.findByCheckOutDateBefore(date);
	}

	// Endpoint to retrieve bookings for a specific user ID and status.
	@GetMapping("/userIdAndStatus/{userId}/{status}")
	public List<Booking> getBookingsByUserIdAndStatus(@PathVariable("userId") int userId, @PathVariable("status") String status) {
		return service.findByUserIdAndStatus(userId, status);
	}

	// Endpoint to retrieve bookings with a check-in date after a specific date.
	@GetMapping("/checkInDateAfter/{date}")
	public List<Booking> getBookingsByCheckInDateAfter(@PathVariable("date") LocalDate date) {
		return service.findByCheckInDateAfter(date);
	}

}
