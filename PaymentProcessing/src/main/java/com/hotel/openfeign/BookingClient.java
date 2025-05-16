package com.hotel.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.dto.Booking;
import com.hotel.exception.BookingNotFound;

// Declares this interface as a Feign client for the HOTELBOOKINGSERVICE
@FeignClient(name = "HOTELBOOKINGSERVICE", path = "/booking")
public interface BookingClient {

	// /booking/fetchById/{bid} GET endpoint of the HOTELBOOKINGSERVICE
	@GetMapping("/fetchById/{bid}")
	public Booking getBookingById(@PathVariable("bid") int userId) throws BookingNotFound;

	// /booking/updateBooking PUT endpoint of the HOTELBOOKINGSERVICE
	@PutMapping("/updateBooking")
	public String updateBooking(@RequestBody Booking user);

}