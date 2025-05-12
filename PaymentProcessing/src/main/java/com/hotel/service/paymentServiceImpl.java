package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dto.Booking;
import com.hotel.exception.BookingNotFound;
import com.hotel.exception.paymentNotFound;
import com.hotel.model.payment;
import com.hotel.openFeign.BookingClient;
import com.hotel.repository.paymentRepository;

@Service("paymentService")
public class paymentServiceImpl implements paymentService {

	Logger log = LoggerFactory.getLogger(paymentServiceImpl.class);

	@Autowired
	paymentRepository repository;

	@Autowired
	BookingClient bookingClient; 

	@Override
	public String addPayment(payment payment) throws BookingNotFound {
		// Adds a new payment and updates the associated booking status.
		// Throws BookingNotFound if the provided booking ID is invalid.
		int bookingId = payment.getBookingId();
		Booking booking = bookingClient.getBookingById(bookingId);
		if (booking == null)
			throw new BookingNotFound("Booking Id not Found");
		else {
			payment payments = repository.save(payment);
			if (payments != null) {
				booking.setStatus("Completed");
				bookingClient.updateBooking(booking);
				log.info("New Payment is Added");
				return "Payment Successfully";
			} else
				return "Payment UnSuccessfull";

		}
	}

	@Override
	public payment getPaymentById(int id) throws paymentNotFound {
		// Retrieves a payment by its unique identifier.
		// Throws paymentNotFound if no payment is found with the given ID.
		Optional<payment> optional = repository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new paymentNotFound("paymentId is invalid");
	}

	@Override
	public List<payment> getAllPayments() {
		// Retrieves a list of all payments from the database.
		log.info("In paymentServiceImpl getAllpayments method...");
		return repository.findAll();
	}

	@Override
	public String cancelPayment(payment pay) throws BookingNotFound, paymentNotFound {
		// Cancels a payment and updates the associated booking status if the payment is completed.
		// Throws BookingNotFound or paymentNotFound if the booking or payment ID is invalid.
		int paymentId = pay.getPaymentId();
		Optional<payment> pay1 = repository.findById(paymentId);
		if (pay1 != null) {
			String payStatus = pay1.get().getStatus();
			if (payStatus.equals("Completed")) {
				int bookingId = pay1.get().getBookingId();
				Booking book = bookingClient.getBookingById(bookingId);
				book.setStatus("Cancelled");
				bookingClient.updateBooking(book);
				return "Cancelled";
			} else {
				return "Payment Status is Not Completed";
			}
		} else {
			throw new paymentNotFound("payment Id Not Found");
		}

	}

}