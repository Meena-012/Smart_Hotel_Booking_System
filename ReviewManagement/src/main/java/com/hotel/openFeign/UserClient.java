package com.hotel.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.dto.UserRole;
import com.hotel.exception.UserNotFound;

// Declares this interface as a Feign client for the USERROLEMANAGEMENTSERVICE
@FeignClient(name="USERROLEMANAGEMENTSERVICE" , path="/users")
public interface UserClient {
	// /users/saveuser POST endpoint of the USERROLEMANAGEMENTSERVICE
	@PostMapping("/saveuser")
	public String addUser(@RequestBody UserRole user);

	// /users/fetchById/{uid} GET endpoint of the USERROLEMANAGEMENTSERVICE
	@GetMapping("/fetchById/{uid}")
	public UserRole getUser(@PathVariable("uid") int userId) throws UserNotFound;

}