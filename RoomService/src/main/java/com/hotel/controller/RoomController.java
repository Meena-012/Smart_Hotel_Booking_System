package com.hotel.controller;

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

import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomLimitExceededException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Room;
import com.hotel.service.RoomService;

import jakarta.validation.Valid;

//REST controller for managing room related operations.
//Provides endpoints to add,update,delete,and retrieve users
@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	RoomService service; // Injecting RoomService dependency

	@PostMapping("/addRoom")
	//@Valid is used the display the validation messages in postman
	//@RequestBody is the annotation that directs Spring to process the HTTP request body.
	// Endpoint to add a new room
	public String addRoom(@Valid @RequestBody Room room) throws HotelNotFoundException, RoomLimitExceededException, RoomNotFound { 
		return service.addRoom(room);
	}

	// Endpoint to update an existing room
	@PutMapping("/updateRoom")
	public String updateRoom(@Valid @RequestBody Room room) { 
		return service.updateRoom(room);
	}

	@DeleteMapping("/deleteRoom/{did}")
	// Endpoint to delete a room by ID
	public String deleteRoom(@PathVariable("did") int roomId) { 
		return service.deleteRoom(roomId);
	}

	// Endpoint to fetch a room by ID
	@GetMapping("/fetchById/{rid}")
	public Room getRoomById(@PathVariable("rid") int roomId) throws RoomNotFound { 
		return service.getRoomById(roomId);
	}

	@GetMapping("/fetchAllRooms")
	// Endpoint to fetch all rooms
	public List<Room> getAllRooms() {
		return service.getAllRooms();
	}

	@GetMapping("/getRoomsByType/{type}")
	// Endpoint to fetch rooms by type
	public List<Room> getRoomsByType(@PathVariable("type") String type) { 
		return service.findByType(type);
	}

	@GetMapping("/PriceLessThan/{price}")
	// Endpoint to fetch rooms with price less than a given value
	public List<Room> getRoomsByPriceLessThan(@PathVariable("price") double price) { 
		return service.findByPriceLessThan(price);
	}

	// Endpoint to fetch rooms with features containing a specific string
	@GetMapping("/featuresContaining/{features}")
	public List<Room> getRoomsByFeaturesContaining(@PathVariable("features") String features) { 
		return service.findByFeaturesContaining(features);
	}

}