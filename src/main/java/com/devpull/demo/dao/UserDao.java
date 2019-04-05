package com.devpull.demo.dao;

import java.util.List;

import com.devpull.demo.model.User;

public interface UserDao {

	public List<User> findAll();
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public void saveUser(User user);
	public void deleteUserById(int id);
	public void update(User user);
	public boolean userExists(User user);
	
	
}
