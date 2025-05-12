package com.hotel.exception;

//@SuppressWarnings will ignore all the unchecked warning 
@SuppressWarnings("serial")

//ReviewNotFound exception thrown when a review is not found in the system.
public class ReviewNotFound extends Exception {
	public ReviewNotFound(String message) {
		super(message);
	}
}
