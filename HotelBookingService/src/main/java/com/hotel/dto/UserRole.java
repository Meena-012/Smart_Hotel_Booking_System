package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data Transfer Object for representing user role information
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
	private int userId; // Unique identifier for the user
	private String userName; // Name of the user
	private String userEmail; // Email address of the user
	private String userPassword; // Password of the user
	private String role; // Role of the user (e.g., "admin", "customer")

}