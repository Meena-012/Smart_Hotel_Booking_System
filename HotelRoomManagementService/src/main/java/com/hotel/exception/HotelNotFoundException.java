package com.hotel.exception;
//@SuppressWarnings will ignore all the unchecked warning 
@SuppressWarnings("serial")

//HotelNotFound exception thrown when a hotel is not found in the system.
public class HotelNotFoundException extends Exception {
	public HotelNotFoundException(String message) {
		super(message);
	}
}
