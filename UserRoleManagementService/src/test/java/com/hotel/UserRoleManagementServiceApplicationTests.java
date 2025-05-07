package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

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
		UserRole userRole=new UserRole(1,"Meena","meena@gmail.com","meena","admin");
		Mockito.when(repository.save(userRole)).thenReturn(userRole);
		String response=service.addUser(userRole);
		assertEquals("User saved Successfully!!", response);

	}
}
