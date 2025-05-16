package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Payment;

// Indicates this interface is a Spring Data JPA repository
@Repository("paymentRepository")
// Extends JpaRepository to provide CRUD operations for the Payment entity with Integer as the ID type
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}