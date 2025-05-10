package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.exception.UserNotFound;
import com.hotel.model.UserRole;
import com.hotel.repository.UserRoleRepository;

import jakarta.validation.Valid;

//Service implementation for managing user roles.
@Service("employeeService")
public class UserRoleServiceImpl implements UserRoleService {

	// Logger instance for logging information and error messages.
	Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);

	@Autowired
	UserRoleRepository repository;

	// Adds a new user to the database.
	@Override
	public String addUser(UserRole user) {
		log.info("In UserRoleServiceImpl addUser method...");
		UserRole users = repository.save(user);
		if (users != null)
			return "User saved Successfully!!";
		else {
			log.error("Failed to save the user");
			return "User not saved";
		}
	}

	// Updates an existing user's information.
	@Override
	public String updateUser(UserRole user) {
		log.info("In UserRoleServiceImpl updateUser method...");
		UserRole users = repository.save(user);
		if (users != null)
			return "User Updated Successfully";
		else {
			log.error("Failed to update the user");
			return "User not Update";
		}
	}

	// Deletes a user by their ID.
	@Override
	public String deleteUser(@Valid int userId) {
		log.info("In UserRoleServiceImpl deleteUser method...");
		repository.deleteById(userId);
		return "User Deleted Successfully";
	}

	// Retrieves a user by their ID.
	@Override
	public UserRole getUser(@Valid int userId) throws UserNotFound {
		log.info("In UserRoleServiceImpl getUser method...");
		Optional<UserRole> optional = repository.findById(userId);
		if (optional.isPresent())
			return optional.get();
		else {
			log.error("User Doesn't exist");
			throw new UserNotFound("UserId is invalid");
		}
	}

	// Retrieves all users from the database.
	@Override
	public List<UserRole> getAllUser() {
		log.info("In UserRoleServiceImpl getAllUser method...");
		return repository.findAll();
	}
}
