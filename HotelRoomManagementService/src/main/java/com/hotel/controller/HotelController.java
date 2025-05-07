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

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	HotelService service;

	@PostMapping("/saveHotel")
	public String saveHotel(@RequestBody Hotels hotel) {
		return service.saveHotel(hotel);
	}

	@PutMapping("/updateHotel")
	public String updateHotel(@RequestBody Hotels hotel) {
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

	@GetMapping("/getAllHotel")
	public List<Hotels> getAllHotel() {
		return service.getAllHotel();
	}

}
