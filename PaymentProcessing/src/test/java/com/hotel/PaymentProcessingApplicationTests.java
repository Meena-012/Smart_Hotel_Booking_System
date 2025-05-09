package com.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
		when(bookingClient.getBookingById(302)).thenReturn(booking);
		payment pay = new payment(502, 4, 302, 5000, "Completed", "Credit Card");
		when(repository.save(pay)).thenReturn(pay);
		String response = service.addPayment(pay);
		assertEquals("Payment Successfully", response);
	}

	@Test
	void getPaymentByIdTest() throws paymentNotFound {
		payment pay = new payment();
		Mockito.when(repository.findById(502)).thenReturn(Optional.of(pay));
		payment response = service.getPaymentById(502);
		assertEquals(pay, response);
	}
	
}
