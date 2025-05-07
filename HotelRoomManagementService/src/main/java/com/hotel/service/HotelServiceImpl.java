package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dto.HotelRoomResponseDTO;
import com.hotel.dto.Room;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Hotels;
import com.hotel.openFeign.RoomClient;
import com.hotel.repository.HotelRepository;

@Service("hotelservice")
public class HotelServiceImpl implements HotelService {

	Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	HotelRepository repository;
	
	@Autowired
	RoomClient roomClient;

	@Override
	public String saveHotel(Hotels hotel) {
		log.info("In HotelServiceImpl saveHotel method...");
		Hotels hotels = repository.save(hotel);
		if (hotels != null)
			return "Hotel Information Saved Successfully!!!";
		else 
			return "Hotel Information Doesn't Saved";
	}

	@Override
	public String updateHotel(Hotels hotel) {
		log.info("In HotelServiceImpl updateHotel method...");
		Hotels hotels = repository.save(hotel);
		if (hotels != null)
			return "Hotel Information Updated Successfully!!!";
		else
			log.error("Failed to update hotel information");
		return "Hotel Information Doesn't Updated";
	}

	@Override
	public String deleteHotel(int id) {
		log.info("In HotelServiceImpl deleteHotel method...");
		repository.deleteById(id);
		return "Hotel Information Deleted Successfully";
	}

	@Override
	public HotelRoomResponseDTO fetchHotelById(int id) throws HotelNotFoundException,RoomNotFound {
		log.info("In HotelServiceImpl fetchById method...");
		Hotels hotel=repository.findById(id).get();
		int room=hotel.getRoomId();
		Room rm=roomClient.getRoomById(room);
		HotelRoomResponseDTO responseDTO = new HotelRoomResponseDTO(hotel, rm);
		return responseDTO;
		
	}
	
	@Override
	public Hotels fetchById(int id) throws HotelNotFoundException {
		Optional<Hotels> optional = repository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new HotelNotFoundException("HotelId is invalid");
	}

	@Override
	public List<Hotels> getAllHotel() {
		log.info("In HotelServiceImpl getAllHotel method...");
		return repository.findAll();
	}

}
