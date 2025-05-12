package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//This is the main application class for the User Role Management Service.
@SpringBootApplication // combination of @Configuration , @EnableAutoConfiguration and @ComponentScan
public class UserRoleManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRoleManagementServiceApplication.class, args);
	}

}
