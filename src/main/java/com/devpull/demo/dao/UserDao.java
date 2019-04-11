package com.devpull.demo.dao;

import java.util.List;


import com.devpull.demo.model.Role;
import com.devpull.demo.model.User;

public interface UserDao {

	public List<User> findAll();
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public void saveUser(User user);
	public void deleteUserById(int id);
	public void update(User user);
	public boolean userExists(User user);
	
	public List<User> findAllCompanies();
	public List<User> findAllUsers();
	
	public Role findByRole(String role);
		
	public User getUser(String username, String password);
	
	public void saveCompany(User user);
	
//	public int registerUser(User user);
	
	
//	public boolean validateUser(User user);
}
