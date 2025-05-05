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

import com.hotel.exception.RoomNotFound;
import com.hotel.model.Room;
import com.hotel.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	RoomService service;

	@PostMapping("/addRoom")
	public String addRoom(@RequestBody Room room) {
		return service.addRoom(room);
	}

	@PutMapping("/updateRoom/{uid}")
	public String updateRoom(@RequestBody Room room) {
		return service.updateRoom(room);
	}

	@DeleteMapping("/deleteRoom/{did}")
	public String deleteRoom(@PathVariable("did") int roomId) {
		return service.deleteRoom(roomId);
	}

	@GetMapping("/fetchById/{rid}")
	public Room getRoomById(@PathVariable("rid") int roomId) throws RoomNotFound {
		return service.getRoomById(roomId);
	}

	@GetMapping("/fetchAllRooms")
	public List<Room> getAllRooms() {
		return service.getAllRooms();
	}

}
