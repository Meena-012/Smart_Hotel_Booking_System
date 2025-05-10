package com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.exception.BookingNotFound;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Booking;

public interface BookingService {

	public abstract String addBooking(Booking booking)throws HotelNotFoundException, RoomNotFound;

	public abstract String updateBooking(Booking booking)  throws HotelNotFoundException, RoomNotFound;

	public abstract String deleteBooking(int bookingId);

	public abstract Booking getBookingById(int id) throws BookingNotFound;

	public abstract List<Booking> getAllBooking();
	
	public abstract List<Booking> findByStatus(String status);

	public abstract List<Booking> findByCheckInDateAfter(LocalDate date);
	
	public abstract List<Booking> findByCheckOutDateBefore(LocalDate date);

	public abstract List<Booking> findByUserIdAndStatus(int userId, String status);
}
