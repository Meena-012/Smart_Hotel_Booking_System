package com.hotel.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.dto.Hotels;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;

// Declares this interface as a Feign client for the HOTELROOMMANAGEMENTSERVICE
@FeignClient(name = "HOTELROOMMANAGEMENTSERVICE", path = "/hotel")
public interface RoomAvailablity {
	// /hotel/fetchHotelById/{hid} GET endpoint to fetch hotel details by its ID
	@GetMapping("/fetchHotelById/{hid}")
	public Hotels fetchHotelById(@PathVariable("hid") int id) throws HotelNotFoundException, RoomNotFound;

}