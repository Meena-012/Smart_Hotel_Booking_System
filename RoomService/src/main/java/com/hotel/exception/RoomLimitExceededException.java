package com.hotel.exception;
//@SuppressWarnings will ignore all the unchecked warning 
@SuppressWarnings("serial")

//RoomNotFound exception thrown when a user is not found in the system.
public class RoomLimitExceededException extends Exception{
	public RoomLimitExceededException(String message) {
		super(message);
	}
}
