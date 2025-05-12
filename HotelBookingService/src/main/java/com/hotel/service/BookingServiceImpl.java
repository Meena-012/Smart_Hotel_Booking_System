package com.hotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dto.Hotels;
import com.hotel.exception.BookingNotFound;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.exception.RoomNotFound;
import com.hotel.model.Booking;
import com.hotel.openFeign.RoomAvailablity;
import com.hotel.repository.BookingRepository;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	BookingRepository repository; // Handles database operations for Booking entities.

	@Autowired
	RoomAvailablity roomAvailablity; // Interacts with the Hotel/Room availability service.

	@Override
	public String addBooking(Booking booking) throws HotelNotFoundException, RoomNotFound {
		// Adds a new booking and decreases the available room count in the hotel.
		// Throws HotelNotFoundException if the hotel is not found.
		int hotelId = booking.getHotelId();
		Hotels hotel = roomAvailablity.fetchHotelById(hotelId);
		if (hotel != null) {
			int roomCount = hotel.getRoomCount();
			if (roomCount >= 1) {
				roomCount = roomCount - 1;
				hotel.setRoomCount(roomCount);

				Booking bookings = repository.save(booking);
				if (bookings != null) {
					roomAvailablity.updateHotel(hotel);
					return "Booking Information saved Successfully!!";
				} else {
					return "Something Wrong with Booking";
				}
			} else {
				return "Room is Not Available";
			}
		} else {
			throw new HotelNotFoundException("Hotel not found");
		}
	}

	@Override
	public String updateBooking(Booking booking) throws HotelNotFoundException, RoomNotFound {
		// Updates an existing booking and potentially increases room count if status
		// changes to "Completed".
		// Throws HotelNotFoundException if the associated hotel is not found.
		if (booking.getStatus().equals("Completed")) {
			int hotelId = booking.getHotelId();
			Hotels hotel = roomAvailablity.fetchHotelById(hotelId);
			if (hotel != null) {
				int roomCount = hotel.getRoomCount();
				roomCount = roomCount + 1;
				hotel.setRoomCount(roomCount);
				roomAvailablity.updateHotel(hotel);
				return "Increased Successfully";
			} else {
				throw new HotelNotFoundException("Hotel Id Not Found");
			}
		} else {
			log.info("In BookingServiceImpl updateBooking method...");
			Booking bookings = repository.save(booking);
			if (bookings != null)
				return "Booking Information Updated Successfully!!";
			else
				return "Something Wrong with Booking Update";
		}
	}

	@Override
	public String deleteBooking(int id) {
		// Deletes a booking by its ID.
		log.info("In BookingServiceImpl deleteBooking method...");
		repository.deleteById(id);
		return "Booking Deleted Successfully";
	}

	@Override
	public Booking getBookingById(int id) throws BookingNotFound {
		// Retrieves a booking by its ID.
		// Throws BookingNotFound if the booking ID is invalid.
		log.info("In BookingServiceImpl getBookingById method...");
		Optional<Booking> optional = repository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new BookingNotFound("BookingId is invalid");
	}

	@Override
	public List<Booking> getAllBooking() {
		// Retrieves all booking records.
		log.info("In BookingServiceImpl getAllUser method...");
		return repository.findAll();
	}

	@Override
	public List<Booking> findByStatus(String status) {
		// Retrieves bookings based on their status.
		return repository.findByStatus(status);
	}

	@Override
	public List<Booking> findByCheckInDateAfter(LocalDate date) {
		// Retrieves bookings with a check-in date after the specified date.
		return repository.findByCheckInDateAfter(date);
	}

	@Override
	public List<Booking> findByCheckOutDateBefore(LocalDate date) {
		// Retrieves bookings with a check-out date before the specified date.
		return repository.findByCheckOutDateBefore(date);
	}

	@Override
	public List<Booking> findByUserIdAndStatus(int userId, String status) {
		// Retrieves bookings for a specific user ID with a specific status.
		return repository.findByUserIdAndStatus(userId, status);
	}
}