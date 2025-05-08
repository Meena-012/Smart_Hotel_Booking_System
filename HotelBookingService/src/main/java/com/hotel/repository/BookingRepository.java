package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Booking;

@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking,Integer>{

}
