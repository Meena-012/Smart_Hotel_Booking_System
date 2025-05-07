package com.hotel.openFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.dto.Room;

@FeignClient(name = "ROOMSERVICE",path="/rooms")
public interface RoomClient {
	@GetMapping("/fetchById/{rid}")
	public abstract Room getRoomById(@PathVariable("rid") int roomId) ;
	
	

}
