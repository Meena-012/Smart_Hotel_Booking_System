package com.hotel.service;

import java.util.List;

import com.hotel.dto.HotelRoomResponseDTO;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Hotels;

public interface HotelService {

	public abstract String saveHotel(Hotels hotel);

	public abstract String updateHotel(Hotels hotel);

	public abstract String deleteHotel(int id);

	public abstract HotelRoomResponseDTO fetchHotelById(int id) throws HotelNotFoundException,RoomNotFound;

	public abstract List<Hotels> getAllHotel();

	Hotels fetchById(int id) throws HotelNotFoundException;

}
