package com.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.exception.RoomNotFound;
import com.hotel.model.Room;

public interface RoomService {

	public abstract String addRoom(Room room);

	public abstract String updateRoom(Room room);

	public abstract String deleteRoom(int roomId);

	public abstract Room getRoomById(int roomId) throws RoomNotFound;

	public abstract List<Room> getAllRooms();
}
