package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//This is the main application class for the payment Management Service.
@SpringBootApplication // combination of @Configuration , @EnableAutoConfiguration and @ComponentScan
@EnableFeignClients  // used for communication between microservices
public class PaymentProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentProcessingApplication.class, args);
	}

}
