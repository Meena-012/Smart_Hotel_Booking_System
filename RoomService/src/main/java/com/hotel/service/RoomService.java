package com.hotel.service;

import java.util.List;

import com.hotel.exception.RoomNotFound;
import com.hotel.model.Room;

public interface RoomService {

	// Abstract method to add a new room
	public abstract String addRoom(Room room);

	// Abstract method to update an existing room
	public abstract String updateRoom(Room room);

	// Abstract method to delete a room by its ID
	public abstract String deleteRoom(int roomId);

	// Abstract method to retrieve a room by its ID, may throw RoomNotFound exception
	public abstract Room getRoomById(int roomId) throws RoomNotFound;

	// Abstract method to retrieve all rooms
	public abstract List<Room> getAllRooms();

	// Abstract method to find rooms by their type
	public abstract List<Room> findByType(String type);

	// Abstract method to find rooms with price less than a given value
	public abstract List<Room> findByPriceLessThan(double price);

	// Abstract method to find rooms whose features contain a specific keyword
	public abstract List<Room> findByFeaturesContaining(String keyword);

}