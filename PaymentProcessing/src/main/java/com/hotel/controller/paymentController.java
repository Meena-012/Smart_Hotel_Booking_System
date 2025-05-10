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

@RestController
@RequestMapping("/payment")
public class paymentController {

    @Autowired
    paymentService service;

    @PostMapping("/savePayment")
    public String addPayment(@Valid @RequestBody payment payment) throws BookingNotFound {
        return service.addPayment(payment);
    }

    @GetMapping("/fetchById/{pid}")
    public payment getPaymentById(@PathVariable("pid") int paymentId) throws paymentNotFound {
        return service.getPaymentById(paymentId);
    }

    @GetMapping("/fetchAll")
    public List<payment> getAllPayments() {
        return service.getAllPayments();
    }
    
    @DeleteMapping("/cancel")
    public String cancelPayment(@RequestBody payment pay ) throws BookingNotFound, paymentNotFound {
    	return service.cancelPayment(pay);
    }
}
