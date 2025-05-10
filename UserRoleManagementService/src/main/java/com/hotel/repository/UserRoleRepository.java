package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.UserRole;

/* Repository interface for UserRole entity. Provides CRUD operations and query
methods for UserRole using Spring Data JPA.
*/
@Repository("userRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
