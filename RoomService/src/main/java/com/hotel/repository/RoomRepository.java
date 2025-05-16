package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.model.Room;

@Repository("roomRepository")
public interface RoomRepository extends JpaRepository<Room, Integer> {
	public abstract List<Room> findByType(String type);

	public abstract List<Room> findByPriceLessThan(double price);
	
	public abstract List<Room> findByFeaturesContaining(String keyword);
	
	 // Counts the total number of rooms for a specific hotel
    @Query("SELECT COUNT(r) FROM Room r WHERE r.hotelId = :hotelId")
    public abstract int countByHotelId(@Param("hotelId") int hotelId);
    
}
