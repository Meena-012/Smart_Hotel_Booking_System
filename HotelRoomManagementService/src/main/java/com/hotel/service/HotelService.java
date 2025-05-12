package com.hotel.service;

import java.util.List;

import com.hotel.dto.HotelRoomResponseDTO;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Hotels;

public interface HotelService {

	// Defines the abstract class for saving a new hotel.
	public abstract String saveHotel(Hotels hotel);

	// Defines the abstract class for updating an existing hotel.
	public abstract String updateHotel(Hotels hotel);

	// Defines the abstract class for deleting a hotel by its ID.
	public abstract String deleteHotel(int id);

	// Defines the abstract class for fetching a hotel and its rooms by hotel ID.
	public abstract HotelRoomResponseDTO fetchHotelById(int id) throws HotelNotFoundException, RoomNotFound;

	// Defines the abstract class for retrieving all hotels.
	public abstract List<Hotels> getAllHotel();

	// Defines the abstract class for fetching a hotel by its ID.
	Hotels fetchById(int id) throws HotelNotFoundException;

	// Defines the contract for finding hotels by their location.
	public abstract List<Hotels> findByLocation(String location);

	// Defines the abstract class for finding hotels by their name.
	public abstract List<Hotels> findByHotelName(String hotelName);

}