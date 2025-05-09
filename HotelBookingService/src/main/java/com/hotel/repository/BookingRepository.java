package com.hotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Booking;

@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking,Integer>{
	public abstract List<Booking> findByStatus(String status);
	
	public abstract List<Booking> findByCheckInDateAfter(LocalDate date);
	
	public abstract List<Booking> findByCheckOutDateBefore(LocalDate date);

	public abstract List<Booking> findByUserIdAndStatus(int userId, String status);


}
