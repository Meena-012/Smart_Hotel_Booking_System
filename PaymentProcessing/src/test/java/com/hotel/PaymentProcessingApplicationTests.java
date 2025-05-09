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
import com.hotel.exception.paymentNotFound;
import com.hotel.model.payment;
import com.hotel.openFeign.BookingClient;
import com.hotel.repository.paymentRepository;
import com.hotel.service.paymentServiceImpl;

@SpringBootTest
class PaymentServiceImplTests {

	@Mock
	private paymentRepository repository;

	@Mock
	private BookingClient bookingClient;

	@InjectMocks
	private paymentServiceImpl service;

	@Test
	void addPaymentTest() throws BookingNotFound {
		Booking booking = new Booking();
		booking.setBookingid(302);
		booking.setStatus("pending");
		Mockito.when(bookingClient.getBookingById(302)).thenReturn(booking);
		payment pay = new payment(502, 4, 302, 5000, "Completed", "Credit Card");
		Mockito.when(repository.save(pay)).thenReturn(pay);
		String response = service.addPayment(pay);
		assertEquals("Payment Successfully", response);
	}

	@Test
	void getAllPaymentTest() {
		payment bookingOne = new payment(502, 4, 302, 5000, "Completed", "Credit Card");
		payment bookingTwo = new payment(503, 6, 302, 7000, "Completed", "Gpay");
		payment bookingThree = new payment(504, 5, 302, 9000, "Completed", "Debit Card");
		List<payment> payments=new ArrayList<>();
		payments.add(bookingOne);
		payments.add(bookingTwo);
		payments.add(bookingThree);
		Mockito.when(repository.findAll()).thenReturn(payments);
		List<payment> response=service.getAllPayments();
		assertEquals(payments,response);
		
	}
	
	@Test
	void getPaymentByIdTest() throws paymentNotFound {
		int id=502;
		payment pay = new payment();
		Mockito.when(repository.findById(id)).thenReturn(Optional.of(pay));
		payment response = service.getPaymentById(id);
		assertEquals(pay, response);
	}
	
}
