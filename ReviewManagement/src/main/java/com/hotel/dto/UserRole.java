package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String role;

}
