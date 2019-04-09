package com.devpull.demo.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;
import com.devpull.demo.services.TokenService;
import com.devpull.demo.util.CustomErrorType;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/check")
	public ResponseEntity<String> loginUser(@RequestParam String username, 
											@RequestParam String password){
		logger.info("checking credentials");
		
		User user = adminService.getUserByUsername(username);
		
		logger.info("to username einai {}", username);
		
		
		user = adminService.getUser(username, password);
		
		logger.info("lalala");
		
		if(user == null) {
			
			logger.error("There is no such user");
			return new ResponseEntity<String>(new CustomErrorType("Unable to find user with this uname"), HttpStatus.NOT_FOUND);
		}
		
		String token = tokenService.createToken(user);
	
		logger.info(token);
		
		return new ResponseEntity<String>(token,HttpStatus.OK);

	}
	
}
