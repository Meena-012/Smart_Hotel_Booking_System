package com.hotel.service;

import java.util.List;

import com.hotel.exception.BookingNotFound;
import com.hotel.exception.paymentNotFound;
import com.hotel.model.payment;

public interface paymentService {

	public abstract String addPayment(payment payment)throws BookingNotFound;

	public abstract payment getPaymentById(int id) throws paymentNotFound;

	public abstract List<payment> getAllPayments();
	
	public abstract String cancelPayment(payment pay) throws BookingNotFound,paymentNotFound;

}
