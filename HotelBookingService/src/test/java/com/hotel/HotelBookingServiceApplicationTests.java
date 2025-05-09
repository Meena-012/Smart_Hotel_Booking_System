package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.dto.Hotels;
import com.hotel.exception.BookingNotFound;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Booking;
import com.hotel.openFeign.RoomAvailablity;
import com.hotel.repository.BookingRepository;
import com.hotel.service.BookingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HotelBookingServiceApplicationTests {

	@Mock
	private BookingRepository repository;

	@Mock
	private RoomAvailablity roomAvailablity;

	@InjectMocks
	private BookingServiceImpl service;

	@Test
	void addBookingSuccessTest() throws HotelNotFoundException, RoomNotFound {
		Booking booking = new Booking(1, 101, 201, 301, LocalDate.now(), LocalDate.now().plusDays(2), "Pending");
		Hotels hotel = new Hotels(301, "Taj", "Chennai", 1, 201, "Amenities", 2, 5);
		Mockito.when(roomAvailablity.fetchHotelById(301)).thenReturn(hotel);
		Mockito.when(repository.save(booking)).thenReturn(booking);
		String response=service.addBooking(booking);
		assertEquals("Booking Information saved Successfully!!", response);
	}

	@Test
	void addBookingHotelNotFoundTest() throws HotelNotFoundException, RoomNotFound {
		Booking booking = new Booking(1, 101, 201, 999, LocalDate.now(), LocalDate.now().plusDays(2), "Pending");
		Mockito.when(roomAvailablity.fetchHotelById(999)).thenReturn(null);
		assertThrows(HotelNotFoundException.class, () -> service.addBooking(booking));
	}

	@Test
	void addBookingRoomNotAvailableTest() throws HotelNotFoundException, RoomNotFound {
		Booking booking = new Booking(1, 101, 201, 301, LocalDate.now(), LocalDate.now().plusDays(2), "Pending");
		Hotels hotel = new Hotels(301, "Taj", "Chennai", 1, 201, "Amenities", 0, 5);

		Mockito.when(roomAvailablity.fetchHotelById(301)).thenReturn(hotel);
		String response = service.addBooking(booking);
		assertEquals("Room is Not Available", response);
	}

	@Test
	void updateBookingTest() {
		Booking booking = new Booking(1, 101, 201, 301, LocalDate.now(), LocalDate.now().plusDays(2), "Confirmed");
		Mockito.when(repository.save(booking)).thenReturn(booking);
		String response = service.updateBooking(booking);
		assertEquals("Booking Information Updated Successfully!!", response);
	}

	@Test
	void deleteBookingTest() {
		doNothing().when(repository).deleteById(1);
		String response = service.deleteBooking(1);
		assertEquals("Booking Deleted Successfully", response);
	}

	@Test
	void getBookingByIdTest() throws BookingNotFound {
		Booking booking = new Booking(1, 101, 201, 301, LocalDate.now(), LocalDate.now().plusDays(2), "Confirmed");
		Mockito.when(repository.findById(1)).thenReturn(Optional.of(booking));
		Booking result = service.getBookingById(1);
		assertEquals(booking, result);
	}

	@Test
	void getBookingByIdNotFoundTest() {
		Mockito.when(repository.findById(1)).thenReturn(Optional.empty());
		assertThrows(BookingNotFound.class, () -> service.getBookingById(1));
	}

	@Test
	void getAllBookingTest() {
		List<Booking> bookings = Arrays.asList(
				new Booking(1, 101, 201, 301, LocalDate.now(), LocalDate.now().plusDays(2), "Confirmed"),
				new Booking(2, 102, 202, 302, LocalDate.now(), LocalDate.now().plusDays(3), "Pending"));
		Mockito.when(repository.findAll()).thenReturn(bookings);
		List<Booking> result = service.getAllBooking();
		assertEquals(bookings, result);
	}

	@Test
	void findByStatusTest() {
		List<Booking> bookings = Arrays
				.asList(new Booking(1, 101, 201, 301, LocalDate.now(), LocalDate.now().plusDays(2), "Confirmed"));
		Mockito.when(repository.findByStatus("Confirmed")).thenReturn(bookings);
		List<Booking> result = service.findByStatus("Confirmed");
		assertEquals(bookings, result);
	}

	@Test
	void findByCheckInDateAfterTest() {
		LocalDate date = LocalDate.now();
		List<Booking> bookings = Arrays
				.asList(new Booking(1, 101, 201, 301, date.plusDays(1), date.plusDays(3), "Confirmed"));
		Mockito.when(repository.findByCheckInDateAfter(date)).thenReturn(bookings);
		List<Booking> result = service.findByCheckInDateAfter(date);
		assertEquals(bookings, result);
	}

	@Test
	void findByCheckOutDateBeforeTest() {
		LocalDate date = LocalDate.now().plusDays(5);
		List<Booking> bookings = Arrays
				.asList(new Booking(1, 101, 201, 301, date.minusDays(3), date.minusDays(1), "Confirmed"));
		Mockito.when(repository.findByCheckOutDateBefore(date)).thenReturn(bookings);
		List<Booking> result = service.findByCheckOutDateBefore(date);
		assertEquals(bookings, result);
	}

	@Test
	void findByUserIdAndStatusTest() {
		List<Booking> bookings = Arrays
				.asList(new Booking(1, 101, 201, 301, LocalDate.now(), LocalDate.now().plusDays(2), "Confirmed"));
		Mockito.when(repository.findByUserIdAndStatus(101, "Confirmed")).thenReturn(bookings);
		List<Booking> result = service.findByUserIdAndStatus(101, "Confirmed");
		assertEquals(bookings, result);
	}
}
