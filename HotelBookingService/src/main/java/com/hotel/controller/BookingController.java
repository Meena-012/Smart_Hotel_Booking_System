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

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService service;

	@PostMapping("/savebooking")
	public String addBooking(@Valid @RequestBody Booking user) throws HotelNotFoundException, RoomNotFound {
		return service.addBooking(user);
	}

	@PutMapping("/updateBooking")
	public String updateBooking(@Valid @RequestBody Booking user) {
		return service.updateBooking(user);
	}

	@DeleteMapping("/deleteBooking/{did}")
	public String deleteBooking(@PathVariable("did") int userId) {
		return service.deleteBooking(userId);
	}

	@GetMapping("/fetchById/{bid}")
	public Booking getBookingById(@PathVariable("bid") int userId) throws BookingNotFound {
		return service.getBookingById(userId);
	}

	@GetMapping("fetchAll")
	public List<Booking> getAllBooking() {
		return service.getAllBooking();
	}

	@GetMapping("getByStatus/{status}")
	public List<Booking> findByStatus(@PathVariable("status") String status) {
		return service.findByStatus(status);
	}

	@GetMapping("/checkOutDateBefore/{date}")
	public List<Booking> getBookingsByCheckOutDateBefore(@PathVariable("date") LocalDate date) {
		return service.findByCheckOutDateBefore(date);
	}

	@GetMapping("/userIdAndStatus/{userId}/{status}")
	public List<Booking> getBookingsByUserIdAndStatus(@PathVariable("userId") int userId,
			@PathVariable("status") String status) {
		return service.findByUserIdAndStatus(userId, status);
	}
	
	@GetMapping("/checkInDateAfter/{date}")
	public List<Booking> getBookingsByCheckInDateAfter(@PathVariable("date") LocalDate date) {
	    return service.findByCheckInDateAfter(date);
	}


}
