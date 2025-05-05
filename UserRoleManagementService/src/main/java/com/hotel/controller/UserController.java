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

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRoleService service;

	@PostMapping("/saveuser")
	public String addUser(@RequestBody UserRole user) {
		return service.addUser(user);
	}

	@PutMapping("/updateuser")
	public String updateUser(@RequestBody UserRole user) {
		return service.updateUser(user);
	}

	@DeleteMapping("/deleteuser/{did}")
	public String deleteUser(@PathVariable("did") int userId) {
		return service.deleteUser(userId);
	}

	@GetMapping("/fetchById/{uid}")
	public UserRole getUser(@PathVariable("uid") int userId) throws UserNotFound{
		return service.getUser(userId);
	}

	@GetMapping("fetchAll")
	public List<UserRole> getAllUser() {
		return service.getAllUser();
	}

}
