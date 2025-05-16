package com.hotel.exception;

//@SuppressWarnings will ignore all the unchecked warning 
@SuppressWarnings("serial")

//PaymentNotFound exception thrown when a paymet is not found in the system.
public class PaymentNotFound extends Exception {
	public PaymentNotFound(String message) {
		super(message);
	}

}
