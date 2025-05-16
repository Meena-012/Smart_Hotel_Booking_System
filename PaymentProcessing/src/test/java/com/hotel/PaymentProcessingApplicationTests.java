package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.dto.Booking;
import com.hotel.exception.BookingNotFound;
import com.hotel.exception.PaymentNotFound;
import com.hotel.exception.UserNotFound;
import com.hotel.model.Payment;
import com.hotel.openfeign.BookingClient;
import com.hotel.repository.PaymentRepository;
import com.hotel.service.PaymentServiceImpl;

@SpringBootTest
class PaymentServiceImplTests {

	@Mock
	private PaymentRepository repository;

	@Mock
	private BookingClient bookingClient;

	@InjectMocks
	private PaymentServiceImpl service;

	@Test
	void addPaymentTest() throws BookingNotFound, UserNotFound {
		Booking booking = new Booking();
		booking.setBookingid(302);
		booking.setStatus("pending");
		Mockito.when(bookingClient.getBookingById(302)).thenReturn(booking);
		Payment pay = new Payment(4, 302, 5000, "Completed", "Credit Card");
		Mockito.when(repository.save(pay)).thenReturn(pay);
		String response = service.addPayment(pay);
		assertEquals("Payment Successfully", response);
	}

	@Test
	void getAllPaymentTest() {
		Payment bookingOne = new Payment(4, 302, 5000, "Completed", "Credit Card");
		Payment bookingTwo = new Payment(6, 302, 7000, "Completed", "Gpay");
		Payment bookingThree = new Payment(5, 302, 9000, "Completed", "Debit Card");
		List<Payment> payments=new ArrayList<>();
		payments.add(bookingOne);
		payments.add(bookingTwo);
		payments.add(bookingThree);
		Mockito.when(repository.findAll()).thenReturn(payments);
		List<Payment> response=service.getAllPayments();
		assertEquals(payments,response);
		
	}
	
	@Test
	void getPaymentByIdTest() throws PaymentNotFound {
		int id=502;
		Payment pay = new Payment(id, id, id, null, null);
		Mockito.when(repository.findById(id)).thenReturn(Optional.of(pay));
		Payment response = service.getPaymentById(id);
		assertEquals(pay, response);
	}
	
}
