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
        // Creating a new user role object
        UserRole userRole = new UserRole(1, "Meena", "meena@gmail.com", "meena", "admin");
        // Mocking repository save method to return the same object
        Mockito.when(repository.save(userRole)).thenReturn(userRole);
        String response = service.addUser(userRole);
        // Asserting the expected success message
        assertEquals("User saved Successfully!!", response);
    }

    @Test
    void updateTest() {
        // Creating an updated user role object
        UserRole userRole = new UserRole(1, "Meenakshi", "meenakshi@gmail.com", "meena", "admin");
        // Mocking repository save method to return the updated object
        Mockito.when(repository.save(userRole)).thenReturn(userRole);
        String response = service.updateUser(userRole);
        // Verifying that the response message matches the expected outcome
        assertEquals("User Updated Successfully", response);
    }

    @Test
    void getUserTest() throws UserNotFound {
        // Creating a new user role object to mock retrieval
        UserRole userRole = new UserRole();
        // Mocking repository findById method to return the user object
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(userRole));
        UserRole response = service.getUser(1);
        // Ensuring the retrieved object matches the expected result
        assertEquals(userRole, response);
    }

    @Test
    void deleteUserTest() {
        // Setting up user ID to be deleted
        int userId = 1;
        // Mocking repository deleteById method to do nothing
        Mockito.doNothing().when(repository).deleteById(userId);
        String response = service.deleteUser(userId);
        // Asserting that the deletion message is correct
        assertEquals("User Deleted Successfully", response);
    }

    @Test
    void getAllUserTest() {
        // Creating a list of multiple user roles
        UserRole userOne = new UserRole(1, "Meena", "meena@gmail.com", "meena", "admin");
        UserRole userTwo = new UserRole(2, "Suresh", "suresh@gmail.com", "suresh", "guest");
        UserRole userThree = new UserRole(3, "Vani", "vani@gmail.com", "vani", "manager");
        List<UserRole> users = new ArrayList<>();
        users.add(userOne);
        users.add(userTwo);
        users.add(userThree);
        // Mocking repository findAll method to return the predefined list
        when(repository.findAll()).thenReturn(users);
        List<UserRole> response = service.getAllUser();
        // Ensuring the retrieved user list matches the expected data
        assertEquals(users, response);
    }
}
