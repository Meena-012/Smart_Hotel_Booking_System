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
	void RoomTest() {
		Room room = new Room(201, 120, 3, "Double", 3500.00, "AC, TV, Kitchenette, City View");
		Mockito.when(repository.save(room)).thenReturn(room);
		String response = service.addRoom(room);
		assertEquals("Room Information Saved Successfully!!", response);
	}

	@Test
	void updateRoomTest() {
		Room room = new Room(201, 120, 3, "Double", 4500.00, "AC, TV, City View");
		Mockito.when(repository.save(room)).thenReturn(room);
		String response = service.updateRoom(room);
		assertEquals("Room Information Updated Successfully!!", response);
	}

	@Test
	void deleteRoomTest() {
		int roomId = 201;
		Mockito.doNothing().when(repository).deleteById(roomId);
		String response = service.deleteRoom(roomId);
		assertEquals("Room Info Deleted Successfully", response);
	}

	@Test
	void getRoomTest() throws RoomNotFound {
		Room room = new Room();
		Mockito.when(repository.findById(201)).thenReturn(Optional.of(room));
		Room response = service.getRoomById(201);
		assertEquals(room, response);
	}
	
	@Test 
	void getAllRoomTest() {
		Room roomOne = new Room(201, 120, 3, "Double", 3500.00, "AC, TV, Kitchenette, City View");
		Room roomTwo = new Room(202, 121, 2, "Single", 2500.00, "AC, TV, Garden View");
		Room roomThree = new Room(203, 122, 4, "Suite", 5000.00, "AC, TV, Balcony, Sea View");
		List<Room> rooms=new ArrayList<>();
		rooms.add(roomOne);
		rooms.add(roomTwo);
		rooms.add(roomThree);
		Mockito.when(repository.findAll()).thenReturn(rooms);
		List<Room> response=service.getAllRooms();
		assertEquals(rooms, response);	
	}
	@Test
	void getByTypeTest() {
		Room roomOne = new Room(201, 120, 3, "Double", 3500.00, "AC, TV, Kitchenette, City View");
		Room roomTwo = new Room(202, 121, 2, "Double", 2500.00, "AC, TV, Garden View");
		Room roomThree = new Room(203, 122, 4, "Double", 5000.00, "AC, TV, Balcony, Sea View");
		List<Room> rooms=new ArrayList<>();
		rooms.add(roomOne);
		rooms.add(roomTwo);
		rooms.add(roomThree);
		Mockito.when(repository.findByType("Double")).thenReturn(rooms);
		List<Room> response=service.findByType("Double");
		assertEquals(rooms, response);	
	}
	
	@Test
	void getByFeaturesTest() {
		Room roomOne = new Room(201, 120, 3, "Double", 3500.00, "AC, TV, Kitchenette, City View");
		Room roomTwo = new Room(202, 121, 2, "Double", 2500.00, "AC, TV, Garden View");
		Room roomThree = new Room(203, 122, 4, "Double", 5000.00, "AC, TV, Balcony, Sea View");
		List<Room> rooms=new ArrayList<>();
		rooms.add(roomOne);
		rooms.add(roomTwo);
		rooms.add(roomThree);
		Mockito.when(repository.findByFeaturesContaining("AC")).thenReturn(rooms);
		List<Room> response=service.findByFeaturesContaining("AC");
		assertEquals(rooms, response);	
	}
	
	@Test
	void getByPriceLessThanTest() {
		Room roomOne = new Room(201, 120, 3, "Double", 3500.00, "AC, TV, Kitchenette, City View");
		Room roomTwo = new Room(202, 121, 2, "Double", 2500.00, "AC, TV, Garden View");
		Room roomThree = new Room(203, 122, 4, "Double", 5000.00, "AC, TV, Balcony, Sea View");
		List<Room> rooms=new ArrayList<>();
		rooms.add(roomOne);
		rooms.add(roomTwo);
		rooms.add(roomThree);
		Mockito.when(repository.findByPriceLessThan(10000)).thenReturn(rooms);
		List<Room> response=service.findByPriceLessThan(10000);
		assertEquals(rooms, response);	
	}
}
