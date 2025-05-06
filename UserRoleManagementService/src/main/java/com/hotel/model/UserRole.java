package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class UserRole {
	@Id
	@NotNull
	private int userId;

	@NotBlank(message = "User Name Cannot Be Empty..")
	private String userName;

	// regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$"
	@Email(message = "Invalid email format")
	@NotBlank
	private String userEmail;

	/*
	 * @Pattern(regexp = "^(?=.*[A-Z])[A-Za-z\\d](?=.*\\d)[A-Za-z]{6,}$", message =
	 * "Password must contain at least one uppercase letter, one number, and be at
	 * least 6 characters long")
	 */
	@NotBlank(message = "Password cannot be Empty")
	private String userPassword;

	@Pattern(regexp = "^(Admin|Manager|Guest)$", message = "Role must be either Admin, Manager, or Guest")
	@NotBlank(message = "Role cannot be Blank")
	private String role;

}
