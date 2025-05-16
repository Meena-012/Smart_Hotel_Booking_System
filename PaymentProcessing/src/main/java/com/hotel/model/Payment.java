package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity // Marks this class as a JPA entity
@Data // Generates getters, setters, equals, hashCode, and toString methods
@Table(name = "payment_info") // Specifies the database table name for this entity
@RequiredArgsConstructor
public class Payment {

	// Marks this field as the primary key
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
	@SequenceGenerator(name = "payment_seq", sequenceName = "payment_sequence", initialValue = 401, allocationSize = 1)
	private int paymentId;

	// Ensures the user ID is not null
	@NotNull(message = "User ID cannot be null")
	private Integer userId;

	// Ensures the booking ID is not null
	@NotNull(message = "Booking ID cannot be null")
	private Integer bookingId;

	// Ensures the amount is at least 1
	@Min(value = 1, message = "Amount must be at least 1")
	private int amount;

	// Ensures the status is not blank, has a maximum size, and matches a specific
	// pattern
	@NotBlank(message = "Status cannot be blank")
	@Size(max = 50, message = "Status cannot exceed 50 characters")
	@Pattern(regexp = "^(Pending|Completed|Failed)$", message = "Status must be Pending,Completed,Failed")
	private String status;

	// Ensures the payment method is not blank and matches a specific pattern
	@NotBlank(message = "Payment method cannot be blank")
	@Pattern(regexp = "^(GPay|Debit Card|Credit Card|Hot Cash)$", message = "Payment Method must be GPay,Debit Card,Credit Card,Hot Cash")
	private String paymentMethod;

	public Payment( Integer userId,Integer bookingId,int amount,String status,String paymentMethod) {
		super();
		this.userId = userId;
		this.bookingId = bookingId;
		this.amount = amount;
		this.status = status;
		this.paymentMethod = paymentMethod;
	}
	
	
}