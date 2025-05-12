package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//This is the main application class for the Review Management Service.
@SpringBootApplication // combination of @Configuration , @EnableAutoConfiguration and @ComponentScan
@EnableFeignClients //to communicate one microservice to other use @EnableFeignClients
public class ReviewManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewManagementApplication.class, args);
	}

}
