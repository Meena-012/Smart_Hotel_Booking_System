package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marks this class as a JPA entity
@Data // Generates getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
@NoArgsConstructor // Generates a constructor with no arguments
@Table(name = "payment_info") // Specifies the database table name for this entity

public class payment {

	// Marks this field as the primary key
	@Id
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
	@Pattern(regexp = "^(GPay|GPay|Debit Card|Credit Card|Hot Cash)$", message = "Payment Method must be GPay,Debit Card,Credit Card,Hot Cash")
	private String paymentMethod;
}