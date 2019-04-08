package com.devpull.demo.services;

import java.util.List;

import com.devpull.demo.model.Role;
import com.devpull.demo.model.User;

public interface AdminService {

	 List<User> findAll();
	 User getUserById(int id);
	 void saveUser(User user);
	 void update(User user);
	 void deleteUserById(int id);
	 User getUserByUsername(String username);

	 boolean userExists(User user);
	 
	 Role findByRole(String role);
	 
	 //int registerUser(User user);
}
