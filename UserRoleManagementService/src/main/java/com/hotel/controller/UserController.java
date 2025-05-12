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

import com.hotel.exception.UserNotFound;
import com.hotel.model.UserRole;
import com.hotel.service.UserRoleService;

import jakarta.validation.Valid;

//REST controller for managing user-related operations.
//Provides endpoints to add,update,delete,and retrieve users
@RestController @RequestMapping("/users")

public class UserController {

	@Autowired
	UserRoleService service;

	// Endpoint to add a new user role.
	@PostMapping("/saveuser")
	public String addUser(@Valid @RequestBody UserRole user) {
		return service.addUser(user);
	}

	// Endpoint to update an existing user role.
	@PutMapping("/updateuser")
	public String updateUser(@Valid @RequestBody UserRole user) {
		return service.updateUser(user);
	}

	// Endpoint to delete a user role based on the provided user ID.
	@DeleteMapping("/deleteuser/{did}")
	public String deleteUser(@PathVariable("did") int userId) {
		return service.deleteUser(userId);
	}

	// Endpoint to retrieve a user role based on the provided user ID.
	@GetMapping("/fetchById/{uid}")
	public UserRole getUser(@PathVariable("uid") int userId) throws UserNotFound {
		return service.getUser(userId);
	}

	// Endpoint to retrieve all user roles.
	@GetMapping("fetchAll")
	public List<UserRole> getAllUser() {
		return service.getAllUser();
	}

}
