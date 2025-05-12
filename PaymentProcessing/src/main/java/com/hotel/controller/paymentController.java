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
import com.hotel.exception.paymentNotFound;
import com.hotel.model.payment;
import com.hotel.service.paymentService;

import jakarta.validation.Valid;

//REST controller for managing payment-related operations.
//Provides endpoints to add, retrieve, and cancel payments.
@RestController
@RequestMapping("/payment")
public class paymentController {

	@Autowired
	paymentService service; // Injecting the paymentService dependency

	// Endpoint to add a new payment.
	public String addPayment(@Valid @RequestBody payment payment) throws BookingNotFound {
		return service.addPayment(payment);
	}

	// Endpoint to retrieve a payment based on the provided payment ID.
	@GetMapping("/fetchById/{pid}")
	public payment getPaymentById(@PathVariable("pid") int paymentId) throws paymentNotFound {
		return service.getPaymentById(paymentId);
	}

	// Endpoint to retrieve all payments.
	@GetMapping("/fetchAll")
	public List<payment> getAllPayments() {
		return service.getAllPayments();
	}

	// Endpoint to cancel a payment.
	@DeleteMapping("/cancel")
	public String cancelPayment(@RequestBody payment pay) throws BookingNotFound, paymentNotFound {
		return service.cancelPayment(pay);
	}
}
