package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.dto.HotelRoomResponseDTO;
import com.hotel.dto.Room;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.model.Hotels;
import com.hotel.openFeign.RoomClient;
import com.hotel.repository.HotelRepository;
import com.hotel.service.HotelServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HotelRoomManagementServiceApplicationTests {

    @Mock
    HotelRepository repository;

    @Mock
    RoomClient roomClient;

    @InjectMocks
    HotelServiceImpl service;

    @Test
    void saveHotelTest() {
        Hotels hotel = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2);
        when(repository.save(hotel)).thenReturn(hotel);
        String response = service.saveHotel(hotel);
        assertEquals("Hotel Information Saved Successfully!!!", response);
    }

    @Test
    void updateHotelTest() {
        Hotels hotel = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2);
        when(repository.save(hotel)).thenReturn(hotel);
        String response = service.updateHotel(hotel);
        assertEquals("Hotel Information Updated Successfully!!!", response);
    }

    @Test
    void deleteHotelTest() {
        int hotelId = 1;
        doNothing().when(repository).deleteById(hotelId);
        String response = service.deleteHotel(hotelId);
        assertEquals("Hotel Information Deleted Successfully", response);
    }

    @Test
    void fetchHotelByIdTest() throws HotelNotFoundException {
        Hotels hotel = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2);
        Room room1 = new Room(201, 1, 101, "Double", 3500.00, "AC, TV, Kitchenette, City View");
        Room room2 = new Room(202, 1, 102, "Single", 2500.00, "AC, TV, Garden View");
        List<Room> roomList = Arrays.asList(room1, room2);
        when(repository.findById(1)).thenReturn(Optional.of(hotel));
        when(roomClient.getAllRooms()).thenReturn(roomList);
        HotelRoomResponseDTO response = service.fetchHotelById(1);
        HotelRoomResponseDTO expectedResponse = new HotelRoomResponseDTO(hotel, roomList);
        assertEquals(expectedResponse, response);
    }

    @Test
    void fetchHotelByIdNotFoundTest() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        try {
            service.fetchHotelById(1);
        } catch (HotelNotFoundException e) {
            assertEquals("HotelId is invalid", e.getMessage());
        }
    }

    @Test
    void fetchByIdTest() throws HotelNotFoundException {
        Hotels hotel = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2);
        when(repository.findById(1)).thenReturn(Optional.of(hotel));
        Hotels response = service.fetchById(1);
        assertEquals(hotel, response);
    }

    @Test
    void fetchByIdNotFoundTest() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        try {
            service.fetchById(1);
        } catch (HotelNotFoundException e) {
            assertEquals("HotelId is invalid", e.getMessage());
        }
    }

    @Test
    void getAllHotelTest() {
        Hotels hotel1 = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2);
        Hotels hotel2 = new Hotels(103, "Leela Palace", "Bangalore", 2, 202, "Luxury bedding, spa access, minibar, and Wi-Fi", 3);
        List<Hotels> hotelList = Arrays.asList(hotel1, hotel2);
        when(repository.findAll()).thenReturn(hotelList);
        List<Hotels> response = service.getAllHotel();
        assertEquals(hotelList, response);
    }

    @Test
    void findByLocationTest() {
        Hotels hotel = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2);
        List<Hotels> hotelList = Arrays.asList(hotel);
        when(repository.findByLocation("New York")).thenReturn(hotelList);
        List<Hotels> response = service.findByLocation("New York");
        assertEquals(hotelList, response);
    }

    @Test
    void getHotelByRatingGreaterThanTest() {
        Hotels hotel = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2);
        List<Hotels> hotelList = Arrays.asList(hotel);
        when(repository.getHotelByRatingGreaterThan(4)).thenReturn(hotelList);
        List<Hotels> response = service.getHotelByRatingGreaterThan(4);
        assertEquals(hotelList, response);
    }

    @Test
    void findByHotelNameTest() {
        Hotels hotel = new Hotels(102, "Taj", "Chennai", 1,201, "BedSheet,clean rooms,toiletries,and Wi-Fi", 2);
        List<Hotels> hotelList = Arrays.asList(hotel);
        when(repository.findByHotelName("Grand Hotel")).thenReturn(hotelList);
        List<Hotels> response = service.findByHotelName("Grand Hotel");
        assertEquals(hotelList, response);
    }
}
