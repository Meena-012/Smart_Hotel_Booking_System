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

@Entity
@Table(name = "payment_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class payment {

	@Id
	private int paymentId;

	@NotNull(message = "User ID cannot be null")
	private Integer userId;

	@NotNull(message = "Booking ID cannot be null")
	private Integer bookingId;

	@Min(value = 1, message = "Amount must be at least 1")
	private int amount;

	@NotBlank(message = "Status cannot be blank")
	@Size(max = 50, message = "Status cannot exceed 50 characters")
	@Pattern(regexp = "^(Pending|Completed|Failed)$", message="Status must be Pending,Completed,Failed")
	private String status;

	@NotBlank(message = "Payment method cannot be blank")
	@Pattern(regexp = "^(GPay|GPay|Debit Card|Credit Card|Hot Cash)$", message = "Payment Method must be GPay,Debit Card,Credit Card,Hot Cash")
	private String paymentMethod;
}
