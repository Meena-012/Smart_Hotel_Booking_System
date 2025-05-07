package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Hotels;

@Repository("hotelrepository")
public interface HotelRepository extends JpaRepository<Hotels, Integer> {

}
