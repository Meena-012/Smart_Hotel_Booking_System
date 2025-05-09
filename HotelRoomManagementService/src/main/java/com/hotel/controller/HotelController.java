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

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	HotelService service;

	@PostMapping("/saveHotel")
	public String saveHotel(@Valid @RequestBody Hotels hotel) {
		return service.saveHotel(hotel);
	}

	@PutMapping("/updateHotel")
	public String updateHotel(@Valid @RequestBody Hotels hotel) {
		return service.updateHotel(hotel);
	}

	@DeleteMapping("/deleteHotel/{hid}")
	public String deleteHotel(@PathVariable("hid") int id) {
		return service.deleteHotel(id);
	}

	@GetMapping("/fetchById/{hid}")
	public HotelRoomResponseDTO fetchHotelById(@PathVariable("hid") int id)throws HotelNotFoundException , RoomNotFound{
		return service.fetchHotelById(id);
	}
	
	@GetMapping("/fetchHotelById/{hid}")
	public Hotels fetchById(@PathVariable("hid") int id)throws HotelNotFoundException , RoomNotFound{
		return service.fetchById(id);
	}

	@GetMapping("/getAllHotel")
	public List<Hotels> getAllHotel() {
		return service.getAllHotel();
	}
	
	@GetMapping("/getByLocation/{location}")
	public List<Hotels> getByLocation(@PathVariable("location") String Location){
		return service.findByLocation(Location);
	}
	
	@GetMapping("/getByRating/{rid}")
	public List<Hotels> getHotelByRatingGreaterThan(@PathVariable("rid") int rating) {
		return service.getHotelByRatingGreaterThan(rating);
	}
	
	@GetMapping("/getByHotelName/{hotelName}")
	public List<Hotels> getByHotelName(@PathVariable("hotelName") String hotelName){
		return service.findByHotelName(hotelName);
	}
	

}
