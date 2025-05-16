package com.hotel.service;

import java.util.List;

import com.hotel.exception.BookingNotFound;
import com.hotel.exception.PaymentNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Payment;

public interface PaymentService {

	// Abstract method to add a new payment
	public abstract String addPayment(Payment payment) throws BookingNotFound, UserNotFound;

	// Abstract method to retrieve a payment by its ID
	public abstract Payment getPaymentById(int id) throws PaymentNotFound;

	// Abstract method to retrieve all payments
	public abstract List<Payment> getAllPayments();

	// Abstract method to cancel a payment
	public abstract String cancelPayment(Payment pay) throws BookingNotFound, PaymentNotFound;

}