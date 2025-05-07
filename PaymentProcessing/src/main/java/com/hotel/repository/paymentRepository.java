package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.payment;

@Repository("paymentRepository")
public interface paymentRepository extends JpaRepository<payment,Integer>{

}
