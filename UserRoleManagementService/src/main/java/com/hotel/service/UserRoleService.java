package com.hotel.service;

import java.util.List;

import com.hotel.exception.UserNotFound;
import com.hotel.model.UserRole;

/*Interface for user role management services. Provides methods to add, update,
delete, and retrieve user information.
*/
public interface UserRoleService {

	public abstract String addUser(UserRole user);

	public abstract String updateUser(UserRole user);

	public abstract String deleteUser(int userId);

	public abstract UserRole getUser(int userId) throws UserNotFound;

	public abstract List<UserRole> getAllUser();

}
