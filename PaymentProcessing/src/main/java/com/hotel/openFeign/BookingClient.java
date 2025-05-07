package com.hotel.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.dto.Booking;
import com.hotel.exception.BookingNotFound;

@FeignClient(name = "HOTELBOOKINGSERVICE", path = "/booking")
public interface BookingClient {

	@GetMapping("/fetchById/{bid}")
	public Booking getBookingById(@PathVariable("bid") int userId) throws BookingNotFound;
		
	@PutMapping("/updateBooking")
	public String updateBooking(@RequestBody Booking user);

}
