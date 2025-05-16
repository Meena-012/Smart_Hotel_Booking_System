package com.hotel.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.dto.Room;
import com.hotel.exception.RoomNotFound;

import jakarta.validation.Valid;

@FeignClient(name="ROOMSERVICE" , path="/rooms")
public interface RoomClient {
	@PostMapping("/addRoom")
	public String addRoom(@Valid @RequestBody Room room) ;
	

	// Endpoint to fetch a room by ID
	@GetMapping("/fetchById/{rid}")
	public Room getRoomById(@PathVariable("rid") int roomId) throws RoomNotFound ;
}