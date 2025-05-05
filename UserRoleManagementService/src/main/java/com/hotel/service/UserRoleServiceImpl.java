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

@Service("employeeService")
public class UserRoleServiceImpl implements UserRoleService {
	
	Logger log=LoggerFactory.getLogger(UserRoleServiceImpl.class);

	@Autowired
	UserRoleRepository repository;

	@Override
	public String addUser(UserRole user) {
		log.info("In UserRoleServiceImpl addUser method...");
		UserRole users = repository.save(user);
		if (users != null)
			return "User saved Successfully!!";
		else
			//log.warn("Failed to save the user");
			return "User not saved";
	}

	@Override
	public String updateUser(UserRole user) {
		log.info("In UserRoleServiceImpl updateUser method...");
		UserRole users = repository.save(user);
		if (users != null)
			return "User Updated Successfully";
		else
			return "User not Update";
	}

	@Override
	public String deleteUser(int userId) {
		log.info("In UserRoleServiceImpl deleteUser method...");
		repository.deleteById(userId);
		return "User Deleted Successfully";
	}

	@Override
	public UserRole getUser(int userId) throws UserNotFound {
		log.info("In UserRoleServiceImpl getUser method...");
		Optional<UserRole> optional = repository.findById(userId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new UserNotFound("UserId is invalid");
	}

	@Override
	public List<UserRole> getAllUser() {
		log.info("In UserRoleServiceImpl getAllUser method...");
		return repository.findAll();
	}

}
