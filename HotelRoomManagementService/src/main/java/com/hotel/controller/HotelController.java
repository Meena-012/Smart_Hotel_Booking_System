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

import com.hotel.dto.HotelRoomResponseDTO;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Hotels;
import com.hotel.service.HotelService;

import jakarta.validation.Valid;

//REST controller for managing hotel-related operations.
@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	HotelService service; // Injecting the HotelService dependency.

	// Endpoint to save a new hotel.
	@PostMapping("/saveHotel")
	public String saveHotel(@Valid @RequestBody Hotels hotel) {
		return service.saveHotel(hotel);
	}

	// Endpoint to update an existing hotel.
	@PutMapping("/updateHotel")
	public String updateHotel(@Valid @RequestBody Hotels hotel) {
		return service.updateHotel(hotel);
	}

	// Endpoint to delete a hotel by its ID.
	@DeleteMapping("/deleteHotel/{hid}")
	public String deleteHotel(@PathVariable("hid") int id) {
		return service.deleteHotel(id);
	}

	// Endpoint to fetch a hotel and its associated rooms by hotel ID.
	@GetMapping("/fetchById/{hid}")
	public HotelRoomResponseDTO fetchHotelById(@PathVariable("hid") int id)
			throws HotelNotFoundException, RoomNotFound {
		return service.fetchHotelById(id);
	}

	// Endpoint to fetch a hotel by its ID.
	@GetMapping("/fetchHotelById/{hid}")
	public Hotels fetchById(@PathVariable("hid") int id) throws HotelNotFoundException, RoomNotFound {
		return service.fetchById(id);
	}

	// Endpoint to retrieve all hotels.
	@GetMapping("/getAllHotel")
	public List<Hotels> getAllHotel() {
		return service.getAllHotel();
	}

	// Endpoint to retrieve hotels by their location.
	@GetMapping("/getByLocation/{locations}")
	public List<Hotels> getByLocation(@PathVariable("locations") String location) {
		return service.findByLocation(location);
	}

//	@GetMapping("/getByRating/{rid}")
//	public List<Hotels> getHotelByRatingGreaterThan(@PathVariable("rid") int rating) {
//		return service.getHotelByRatingGreaterThan(rating);
//	}

	// Endpoint to retrieve hotels by their name.
	@GetMapping("/getByHotelName/{hName}")
	public List<Hotels> getByHotelName(@PathVariable("hName") String hotelName) {
		return service.findByHotelName(hotelName);
	}

}
