package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.HotelBookingServiceApplication;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Booking;
import com.hotel.repository.BookingRepository;
import com.hotel.service.BookingServiceImpl;

@SpringBootTest
class HotelBookingServiceApplicationTests {

	@Mock
	BookingRepository repository;

	@InjectMocks
	BookingServiceImpl service;

	@Test
	void bookingTest() throws HotelNotFoundException, RoomNotFound  {
		LocalDate inputDate = LocalDate.of(2025, 5, 10);
        LocalDate outputDate = LocalDate.of(2025, 5, 15);
		Booking addbooking = new Booking(301,3,201,101, inputDate,outputDate,"pending");
		Mockito.when(repository.save(addbooking)).thenReturn(addbooking);
		String response=service.addBooking(addbooking);
		assertEquals("Booking Information saved Successfully!!", response);

	}
}
