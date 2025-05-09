package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		Room room = new Room(201, 120, 3, "Double", 3500.00,  "AC, TV, Kitchenette, City View");
		Mockito.when(repository.save(room)).thenReturn(room);
		String response = service.addRoom(room);
		assertEquals("Room Information Saved Successfully!!", response);
	}

	@Test
	void getHotelRoomTest() throws RoomNotFound {
		Room room = new Room();
		Mockito.when(repository.findById(201)).thenReturn(Optional.of(room));
		Room response = service.getRoomById(201);
		assertEquals(room, response);
	}

}
