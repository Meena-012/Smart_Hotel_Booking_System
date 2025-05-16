package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dto.Hotels;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomLimitExceededException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Room;
import com.hotel.openFeign.RoomAvailablity;
import com.hotel.repository.RoomRepository;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository repository; // Injecting RoomRepository dependency

	@Autowired
	RoomAvailablity roomAvailablity;

	@Autowired
	private RoomRepository roomRepository;

	public String addRoom(Room room) throws RoomLimitExceededException, HotelNotFoundException, RoomNotFound {
		int hotelId = room.getHotelId();

		// Fetch hotel details
		Hotels hotel = roomAvailablity.fetchHotelById(hotelId);
		if (hotel == null) {
			throw new HotelNotFoundException("Hotel Id not found");
		}
		int maxRoomCount = hotel.getRoomCount(); // Maximum allowed room count
		int currentRoomCount = roomRepository.countByHotelId(hotelId); // Get existing rooms for hotel

		// Validate room count before adding new rooms
		if (currentRoomCount >= maxRoomCount) {
			throw new RoomLimitExceededException("Cannot add more rooms! Maximum allowed is " + maxRoomCount);
		}

		// Save room since limit is not exceeded
		roomRepository.save(room);
		return "Room added successfully!";
	}

	@Override
	public String updateRoom(Room room) { // Implementation to update an existing room
		Room rooms = repository.save(room); // Saving the updated room to the database
		if (rooms != null) // Checking if the room was updated successfully
			return "Room Information Updated Successfully!!";
		else
			return "Something Went Wrong While Updating Room Info";
	}

	@Override
	public String deleteRoom(int roomId) { // Implementation to delete a room by its ID
		repository.deleteById(roomId); // Deleting the room from the database
		return "Room Info Deleted Successfully";
	}

	@Override
	public Room getRoomById(int roomId) throws RoomNotFound { // Implementation to retrieve a room by its ID, may throw
																// RoomNotFound
		Optional<Room> optional = repository.findById(roomId); // Finding the room by ID in the repository
		if (optional.isPresent()) // Checking if a room with the given ID exists
			return optional.get(); // Returning the found room
		else
			throw new RoomNotFound("RoomId is invalid"); // Throwing RoomNotFound exception if the ID is invalid
	}

	@Override
	public List<Room> getAllRooms() { // Implementation to retrieve all rooms
		return repository.findAll(); // Fetching all rooms from the repository
	}

	@Override
	public List<Room> findByType(String type) { // Implementation to find rooms by their type
		return repository.findByType(type); // Fetching rooms by type from the repository
	}

	@Override
	public List<Room> findByPriceLessThan(double price) { // Implementation to find rooms with price less than a given
															// value
		return repository.findByPriceLessThan(price); // Fetching rooms with price less than the given value from the
														// repository
	}

	@Override
	public List<Room> findByFeaturesContaining(String features) { // Implementation to find rooms whose features contain
																	// a specific keyword
		return repository.findByFeaturesContaining(features); // Fetching rooms with features containing the keyword
																// from the repository
	}

}