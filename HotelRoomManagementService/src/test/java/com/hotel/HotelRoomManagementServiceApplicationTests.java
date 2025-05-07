package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.model.Hotels;
import com.hotel.repository.HotelRepository;
import com.hotel.service.HotelServiceImpl;

@SpringBootTest
class HotelRoomManagementServiceApplicationTests {

	@Mock
	HotelRepository repository;
	@InjectMocks
	HotelServiceImpl service;

	@Test
	void hotelTest() {
		Hotels hotel = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2,5);
		Mockito.when(repository.save(hotel)).thenReturn(hotel);
		String response = service.saveHotel(hotel);
		assertEquals("Hotel Information Saved Successfully!!!", response);
	}

}
