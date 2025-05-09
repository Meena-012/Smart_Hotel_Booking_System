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
		Optional<payment> optional = repository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new paymentNotFound("paymentId is invalid");
	}

	@Override
	public List<payment> getAllPayments() {
		log.info("In paymentServiceImpl getAllpayments method...");
		return repository.findAll();
	}

	
}
