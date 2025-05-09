package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.exception.UserNotFound;
import com.hotel.model.UserRole;
import com.hotel.repository.UserRoleRepository;
import com.hotel.service.UserRoleServiceImpl;

@SpringBootTest
class UserRoleManagementServiceApplicationTests {

	@Mock
	UserRoleRepository repository;

	@InjectMocks
	UserRoleServiceImpl service;

	@Test
	void addUserTest() {
		UserRole userRole = new UserRole(1, "Meena", "meena@gmail.com", "meena", "admin");
		Mockito.when(repository.save(userRole)).thenReturn(userRole);
		String response = service.addUser(userRole);
		assertEquals("User saved Successfully!!", response);

	}

	@Test
	void updateTest() {
		UserRole userRole = new UserRole(1, "Meenakshi", "meenakshi@gmail.com", "meena", "admin");
		Mockito.when(repository.save(userRole)).thenReturn(userRole);
		String response = service.updateUser(userRole);
		assertEquals("User Updated Successfully", response);
	}

	@Test
	void getUserTest() throws UserNotFound {
		UserRole userRole = new UserRole();
		Mockito.when(repository.findById(1)).thenReturn(Optional.of(userRole));
		UserRole response = service.getUser(1);
		assertEquals(userRole, response);
	}

	@Test
	void deleteUserTest() {
		int userId = 1;
		Mockito.doNothing().when(repository).deleteById(userId);
		String response = service.deleteUser(userId);
		assertEquals("User Deleted Successfully", response);
	}

	@Test
	void getAllUserTest() {
		UserRole userOne = new UserRole(1, "Meena", "meena@gmail.com", "meena", "admin");
		UserRole userTwo = new UserRole(2, "Suresh", "suresh@gmail.com", "suresh", "guest");
		UserRole userThree = new UserRole(3, "Vani", "vani@gmail.com", "vani", "manager");
		List<UserRole> users = new ArrayList<>();
		users.add(userOne);
		users.add(userTwo);
		users.add(userThree);
		when(repository.findAll()).thenReturn(users);
		List<UserRole> response = service.getAllUser();
		assertEquals(users, response);
	}
}
