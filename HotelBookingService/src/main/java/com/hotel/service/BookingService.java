package com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.exception.BookingNotFound;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Booking;

public interface BookingService {

	// Defines the contract for adding a new booking.
	public abstract String addBooking(Booking booking) throws HotelNotFoundException, RoomNotFound;

	// Defines the contract for updating an existing booking.
	public abstract String updateBooking(Booking booking) throws HotelNotFoundException, RoomNotFound;

	// Defines the contract for deleting a booking by its ID.
	public abstract String deleteBooking(int bookingId);

	// Defines the contract for retrieving a booking by its ID.
	public abstract Booking getBookingById(int id) throws BookingNotFound;

	// Defines the contract for retrieving all bookings.
	public abstract List<Booking> getAllBooking();

	// Defines the contract for finding bookings by their status.
	public abstract List<Booking> findByStatus(String status);

	// Defines the contract for finding bookings with a check-in date after a
	// specific date.
	public abstract List<Booking> findByCheckInDateAfter(LocalDate date);

	// Defines the contract for finding bookings with a check-out date before a
	// specific date.
	public abstract List<Booking> findByCheckOutDateBefore(LocalDate date);

	// Defines the contract for finding bookings by user ID and status.
	public abstract List<Booking> findByUserIdAndStatus(int userId, String status);
}