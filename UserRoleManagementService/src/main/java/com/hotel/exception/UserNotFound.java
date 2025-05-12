package com.hotel.exception;

//@SuppressWarnings will ignore all the unchecked warning 
@SuppressWarnings("serial")

//UserNotFound exception thrown when a user is not found in the system.
public class UserNotFound extends Exception{
	public UserNotFound(String message) {
		super(message);
	}

}
