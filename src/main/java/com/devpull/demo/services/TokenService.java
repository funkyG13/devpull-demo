package com.devpull.demo.services;

import com.devpull.demo.model.User;

public interface TokenService {

	String createToken(User user);
	 
	User getUserOfToken(String token);
	
}
