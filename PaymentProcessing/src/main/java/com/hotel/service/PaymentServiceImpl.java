package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dto.Booking;
import com.hotel.dto.UserRole;
import com.hotel.exception.BookingNotFound;
import com.hotel.exception.PaymentNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Payment;
import com.hotel.openfeign.BookingClient;
import com.hotel.openfeign.UserClient;
import com.hotel.repository.PaymentRepository;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	PaymentRepository repository;

	@Autowired
	BookingClient bookingClient;

	@Autowired
	UserClient userClient;

	@Override
	public String addPayment(Payment payment) throws BookingNotFound, UserNotFound {
		// Adds a new payment and updates the associated booking status.
		// Throws BookingNotFound if the provided booking ID is invalid.
		// Validate Booking ID
		int bookingId = payment.getBookingId();
		int userId = payment.getUserId();
		Booking booking = bookingClient.getBookingById(bookingId);
		if (booking == null) {
			throw new BookingNotFound("Booking Id not Found");
		}

		// Validate User ID
		UserRole user = userClient.getUser(userId); // Fetch user details
		if (user == null) {
			throw new UserNotFound("User Id Invalid");
		}
		Payment payments = repository.save(payment);
		if (payments != null) {
			booking.setStatus("Completed");
			bookingClient.updateBooking(booking);
			log.info("New Payment is Added");
			return "Payment Successfully";
		} else
			return "Payment UnSuccessfull";

	}

	@Override
	public Payment getPaymentById(int id) throws PaymentNotFound {
		// Retrieves a payment by its unique identifier.
		// Throws paymentNotFound if no payment is found with the given ID.
		Optional<Payment> optional = repository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new PaymentNotFound("paymentId is invalid");
	}

	@Override
	public List<Payment> getAllPayments() {
		// Retrieves a list of all payments from the database.
		log.info("In paymentServiceImpl getAllpayments method...");
		return repository.findAll();
	}

	@Override
	public String cancelPayment(Payment pay) throws BookingNotFound, PaymentNotFound {
		// Cancels a payment only if the user has booked this hotel and made the
		// payment.
		// Throws BookingNotFound, PaymentNotFound, or UnauthorizedCancellation if
		// validation fails.

		int paymentId = pay.getPaymentId();
		int userId = pay.getUserId(); // Get user attempting cancellation
		Optional<Payment> pay1 = repository.findById(paymentId);

		if (pay1.isPresent()) { // Ensure payment exists
			String payStatus = pay1.get().getStatus();
			int bookingId = pay1.get().getBookingId();

			// Retrieve booking details
			Booking book = bookingClient.getBookingById(bookingId);
			if (book != null) {
				if ("Cancelled".equals(book.getStatus())) {
				    return "Booking has already been canceled, further cancellations are not allowed";
				}
				// Validate that the user canceling is the same user who booked the hotel
				if (book.getUserId() != userId) {
					return ("User is not authorized to cancel this booking");
				}
				if (payStatus.equals("Completed")) { // Proceed with cancellation
					book.setStatus("Cancelled");
					bookingClient.updateBooking(book);
					log.info("Updating booking status to: " + book.getStatus());
					return "Booking Cancelled Successfully";
				} else {
					return "Payment Status is Not Completed";
				}
			} else {
				throw new BookingNotFound("Booking ID Not Found");
			}
		} else {
			throw new PaymentNotFound("Payment ID Not Found");
		}
	}

}