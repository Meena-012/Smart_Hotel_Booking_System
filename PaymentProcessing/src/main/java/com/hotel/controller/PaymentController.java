package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.exception.BookingNotFound;
import com.hotel.exception.PaymentNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Payment;
import com.hotel.service.PaymentService;

import jakarta.validation.Valid;

//REST controller for managing payment-related operations.
//Provides endpoints to add, retrieve, and cancel payments.
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService service; // Injecting the paymentService dependency

	// Endpoint to add a new payment.
	@PostMapping("/addpayment")
	public String addPayment(@Valid @RequestBody Payment payment) throws BookingNotFound, UserNotFound {
		return service.addPayment(payment);
	}

	// Endpoint to retrieve a payment based on the provided payment ID.
	@GetMapping("/fetchById/{pid}")
	public Payment getPaymentById(@PathVariable("pid") int paymentId) throws PaymentNotFound {
		return service.getPaymentById(paymentId);
	}

	// Endpoint to retrieve all payments.
	@GetMapping("/fetchAll")
	public List<Payment> getAllPayments() {
		return service.getAllPayments();
	}

	// Endpoint to cancel a payment.
	@DeleteMapping("/cancel")
	public String cancelPayment(@RequestBody Payment pay) throws BookingNotFound, PaymentNotFound {
		return service.cancelPayment(pay);
	}
}
