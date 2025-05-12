package com.hotel.service;

import java.util.List;

import com.hotel.exception.BookingNotFound;
import com.hotel.exception.paymentNotFound;
import com.hotel.model.payment;

public interface paymentService {

	// Abstract method to add a new payment
	public abstract String addPayment(payment payment) throws BookingNotFound;

	// Abstract method to retrieve a payment by its ID
	public abstract payment getPaymentById(int id) throws paymentNotFound;

	// Abstract method to retrieve all payments
	public abstract List<payment> getAllPayments();

	// Abstract method to cancel a payment
	public abstract String cancelPayment(payment pay) throws BookingNotFound, paymentNotFound;

}