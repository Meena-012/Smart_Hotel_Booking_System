package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//This is the main application class for the User Role Management Service.
@SpringBootApplication // combination of @Configuration , @EnableAutoConfiguration and @ComponentScan
@EnableFeignClients //to make communication between microservices
public class HotelBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingServiceApplication.class, args);
	}

}
