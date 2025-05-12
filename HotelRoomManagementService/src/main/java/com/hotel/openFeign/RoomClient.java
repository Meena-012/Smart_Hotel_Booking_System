package com.hotel.openFeign;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.dto.Room;

//Declares this interface as a Feign client for the ROOMSERVICE.
@FeignClient(name = "ROOMSERVICE",path="/rooms")
public interface RoomClient {
	// /rooms/fetchAllRooms GET endpoint of the ROOMSERVICE to retrieve all rooms.
	@GetMapping("/fetchAllRooms")
	public abstract List<Room> getAllRooms() ;

	// /rooms/fetchById/{rid} GET endpoint of the ROOMSERVICE to retrieve a specific room by its ID.
	@GetMapping("/fetchById/{rid}")
	public abstract Room getRoomById(@PathVariable("rid") int id);
	
}