package com.devpull.demo.services;

import java.util.List;
import java.util.Map;

import com.devpull.demo.model.Languages;
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
	 
	 User getUser(String username, String password);
	 
	 void saveCompany(User user);
	 
	 List<User> findAllCompanies();
	
	 List<User> findAllUsers();
	 
	 List<Languages> getAllLanguages();
	 
	 List<Languages> getUsersLanguage(User user);
	 
	 //int registerUser(User user);
}
