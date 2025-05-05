package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.exception.RoomNotFound;
import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository repository;

	@Override
	public String addRoom(Room room) {
		Room rooms = repository.save(room);
		if (rooms != null)
			return "Room Information Saved Successfully!!";
		else
			return "Something Went Wrong While Saving Room Info";
	}

	@Override
	public String updateRoom(Room room) {
		Room rooms = repository.save(room);
		if (rooms != null)
			return "Room Information Updated Successfully!!";
		else
			return "Something Went Wrong While Updating Room Info";
	}

	@Override
	public String deleteRoom(int roomId) {
		repository.deleteById(roomId);
		return "Room Info Deleted Successfully";
	}

	@Override
	public Room getRoomById(int roomId) throws RoomNotFound {
		Optional<Room> optional = repository.findById(roomId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new RoomNotFound("RoomId is invalid");
	}

	@Override
	public List<Room> getAllRooms() {
		return repository.findAll();
	}

}
