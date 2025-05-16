package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomLimitExceededException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomServiceImpl;

@SpringBootTest
class RoomServiceApplicationTests {

    @Mock
    RoomRepository repository;

    @InjectMocks
    RoomServiceImpl service;

    @Test
    void RoomTest() throws RoomLimitExceededException, HotelNotFoundException, RoomNotFound {
        // Creating a new room object with details
        Room room = new Room(201, 120,  "Double", 3500.00, "AC, TV, Kitchenette, City View");
        // Mocking repository save method to return the same room object
        Mockito.when(repository.save(room)).thenReturn(room);
        String response = service.addRoom(room);
        // Asserting that the response message is as expected
        assertEquals("Room Information Saved Successfully!!", response);
    }

    @Test
    void updateRoomTest() {
        // Creating an updated room object with new pricing
        Room room = new Room(201, 120, "Double", 4500.00, "AC, TV, City View");
        // Mocking repository save method to return the updated room object
        Mockito.when(repository.save(room)).thenReturn(room);
        String response = service.updateRoom(room);
        // Ensuring the update success message is correct
        assertEquals("Room Information Updated Successfully!!", response);
    }

    @Test
    void deleteRoomTest() {
        // Specifying a room ID to be deleted
        int roomId = 201;
        // Mocking repository delete method to do nothing
        Mockito.doNothing().when(repository).deleteById(roomId);
        String response = service.deleteRoom(roomId);
        // Checking if the expected deletion success message is returned
        assertEquals("Room Info Deleted Successfully", response);
    }

    @Test
    void getRoomTest() throws RoomNotFound {
        // Creating an empty room object for mock retrieval
        Room room = new Room();
        // Mocking repository findById method to return the room object
        Mockito.when(repository.findById(201)).thenReturn(Optional.of(room));
        Room response = service.getRoomById(201);
        // Validating that the returned object matches the expected one
        assertEquals(room, response);
    }
    
    @Test 
    void getAllRoomTest() {
        // Creating a list of rooms with different attributes
        Room roomOne = new Room(201, 120, "Double", 3500.00, "AC, TV, Kitchenette, City View");
        Room roomTwo = new Room(202, 121, "Single", 2500.00, "AC, TV, Garden View");
        Room roomThree = new Room(203, 122, "Suite", 5000.00, "AC, TV, Balcony, Sea View");
        List<Room> rooms = new ArrayList<>();
        rooms.add(roomOne);
        rooms.add(roomTwo);
        rooms.add(roomThree);
        // Mocking repository findAll method to return the predefined list
        Mockito.when(repository.findAll()).thenReturn(rooms);
        List<Room> response = service.getAllRooms();
        // Ensuring the retrieved list matches the expected room list
        assertEquals(rooms, response);  
    }

    @Test
    void getByTypeTest() {
        // Creating a list of rooms of type "Double"
        Room roomOne = new Room(201, 120, "Double", 3500.00, "AC, TV, Kitchenette, City View");
        Room roomTwo = new Room(202, 121, "Double", 2500.00, "AC, TV, Garden View");
        Room roomThree = new Room(203, 122, "Double", 5000.00, "AC, TV, Balcony, Sea View");
        List<Room> rooms = new ArrayList<>();
        rooms.add(roomOne);
        rooms.add(roomTwo);
        rooms.add(roomThree);
        // Mocking repository findByType method to return the rooms
        Mockito.when(repository.findByType("Double")).thenReturn(rooms);
        List<Room> response = service.findByType("Double");
        // Checking if the retrieved list matches the expected filtered list
        assertEquals(rooms, response);  
    }
    
    @Test
    void getByFeaturesTest() {
        // Creating a list of rooms with the "AC" feature
        Room roomOne = new Room(201, 120, "Double", 3500.00, "AC, TV, Kitchenette, City View");
        Room roomTwo = new Room(202, 121,  "Double", 2500.00, "AC, TV, Garden View");
        Room roomThree = new Room(203, 122,  "Double", 5000.00, "AC, TV, Balcony, Sea View");
        List<Room> rooms = new ArrayList<>();
        rooms.add(roomOne);
        rooms.add(roomTwo);
        rooms.add(roomThree);
        // Mocking repository findByFeaturesContaining method to return the rooms
        Mockito.when(repository.findByFeaturesContaining("AC")).thenReturn(rooms);
        List<Room> response = service.findByFeaturesContaining("AC");
        // Validating that the filtered room list matches the expected results
        assertEquals(rooms, response);  
    }
    
    @Test
    void getByPriceLessThanTest() {
        // Creating a list of rooms with a price below a certain threshold
        Room roomOne = new Room(201, 120,  "Double", 3500.00, "AC, TV, Kitchenette, City View");
        Room roomTwo = new Room(202, 121,  "Double", 2500.00, "AC, TV, Garden View");
        Room roomThree = new Room(203, 122,  "Double", 5000.00, "AC, TV, Balcony, Sea View");
        List<Room> rooms = new ArrayList<>();
        rooms.add(roomOne);
        rooms.add(roomTwo);
        rooms.add(roomThree);
        // Mocking repository findByPriceLessThan method to return rooms below 10,000
        Mockito.when(repository.findByPriceLessThan(10000)).thenReturn(rooms);
        List<Room> response = service.findByPriceLessThan(10000);
        // Checking if the retrieved list matches the expected filtered list
        assertEquals(rooms, response);  
    }
}
