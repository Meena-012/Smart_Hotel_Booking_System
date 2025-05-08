package com.hotel.service;

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
	BookingRepository repository;

	@Autowired
	RoomAvailablity roomAvailablity;
	@Override
	public String addBooking(Booking booking) throws HotelNotFoundException, RoomNotFound {
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
	public String updateBooking(Booking booking) {

		log.info("In BookingServiceImpl updateBooking method...");
		Booking bookings = repository.save(booking);
		if (bookings != null)
			return "Booking Information Updated Successfully!!";
		else
			return "Something Wrong with Booking Update";
	}

	@Override
	public String deleteBooking(int id) {
		log.info("In BookingServiceImpl deleteBooking method...");
		repository.deleteById(id);
		return "Booking Deleted Successfully";
	}

	@Override
	public Booking getBookingById(int id) throws BookingNotFound {
		log.info("In BookingServiceImpl getBookingById method...");
		Optional<Booking> optional = repository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new BookingNotFound("UserId is invalid");
	}

	@Override
	public List<Booking> getAllBooking() {
		log.info("In BookingServiceImpl getAllUser method...");
		return repository.findAll();
	}

}
