package com.devpull.demo.dao;

import com.devpull.demo.model.User;

public interface TokenDao {

	
	public String createToken(User user);
	 
	public User getUserOfToken(String token, UserDao userDao);
}
