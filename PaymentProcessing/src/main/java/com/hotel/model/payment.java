package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private int userId;
	private int bookingId;
	private int amount;
	private String status;
	private String paymentMethod;

}
