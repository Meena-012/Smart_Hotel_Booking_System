package com.hotel.exception;
//@SuppressWarnings will ignore all the unchecked warning 
@SuppressWarnings("serial")

//BookingNotFound exception thrown when a booking is not found in the system.
public class BookingNotFound extends Exception{
	public BookingNotFound(String message) {
		super(message);
	}

}
