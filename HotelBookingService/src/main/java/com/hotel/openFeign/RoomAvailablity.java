package com.hotel.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.dto.HotelRoomResponseDTO;
import com.hotel.dto.Hotels;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;

@FeignClient(name="HOTELROOMMANAGEMENTSERVICE" , url="http://localhost:8082")
public interface RoomAvailablity {
	
	@PutMapping("hotel/updateHotel")
	public String updateHotel(@RequestBody Hotels hotel);
	
	@GetMapping("hotel/fetchHotelById/{hid}")
	public Hotels fetchHotelById(@PathVariable("hid") int id)throws HotelNotFoundException , RoomNotFound;


}
