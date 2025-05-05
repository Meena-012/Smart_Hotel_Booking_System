package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.UserRole;

@Repository("userRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
