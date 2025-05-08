package com.hotel.service;

import java.util.List;

import com.hotel.exception.BookingNotFound;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Booking;

public interface BookingService {

	public abstract String addBooking(Booking booking)throws HotelNotFoundException, RoomNotFound;

	public abstract String updateBooking(Booking booking);

	public abstract String deleteBooking(int bookingId);

	public abstract Booking getBookingById(int id) throws BookingNotFound;

	public abstract List<Booking> getAllBooking();

}
