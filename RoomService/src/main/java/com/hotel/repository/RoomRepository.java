package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Room;

@Repository("roomRepository")
public interface RoomRepository extends JpaRepository<Room, Integer> {
	public abstract List<Room> findByType(String type);

	public abstract List<Room> findByPriceLessThan(double price);
	
	public abstract List<Room> findByFeaturesContaining(String keyword);
}
