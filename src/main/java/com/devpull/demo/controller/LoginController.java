package com.devpull.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devpull.demo.model.User;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	
	@PostMapping
	public ResponseEntity<User> loginUser(@RequestBody User user){
		logger.info("checking credentials");
		
		if(user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
		logger.info("all good");
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	else {
		logger.error("not admin");
		return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}
	}
	
}
