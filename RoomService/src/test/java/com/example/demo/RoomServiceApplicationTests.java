package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.RoomServiceApplication;
import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomServiceImpl;

@SpringBootTest(classes = RoomServiceApplication.class)
class RoomServiceApplicationTests {

	@Mock
	RoomRepository repository;
	
	@InjectMocks
	RoomServiceImpl service;
	@Test
	void RoomTest() {
		Room room=new Room(201,120,"Double",3500.00,false,"AC, TV, Kitchenette, City View");
		Mockito.when(repository.save(room)).thenReturn(room);
		String response=service.addRoom(room);
		assertEquals("Room Information Saved Successfully!!", response);
	}

}
