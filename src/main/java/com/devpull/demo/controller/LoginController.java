package com.devpull.demo.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devpull.demo.model.PersistentLogins;
import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;
import com.devpull.demo.services.TokenService;
import com.devpull.demo.util.CustomErrorType;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LoginController {

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private TokenService tokenService;

	@Autowired
	private AdminService adminService;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {

		User user = adminService.getUser(username, password);

		if (user == null) {

			logger.error("There is no such user");
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("User not found ", 400, new Date()), HttpStatus.BAD_REQUEST);
		}
		
		String token = tokenService.createToken(user);
		
		PersistentLogins pl = new PersistentLogins(user,token); 
		
		logger.info(token);

		return new ResponseEntity<PersistentLogins>(pl, HttpStatus.OK);

	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logoutUser(@RequestParam String token){
		
		User user = tokenService.getUserOfToken(token);

		logger.info("deleting token from user: " + user.toString());
		
		if (user == null || token == null) {
			logger.error("token or user are null");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			
		}
		tokenService.removeToken(token);
		
		logger.info("token succesfully removed " );
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
