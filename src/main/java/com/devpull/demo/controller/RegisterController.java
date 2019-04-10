package com.devpull.demo.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devpull.demo.model.Register;
import com.devpull.demo.model.Role;
import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

	public static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody Register register) {
		
		
		User user = new User();
		
		user.setId(register.getId());
		user.setFirstName(register.getFirstName());
		user.setLastName(register.getLastName());
		user.setUsername(register.getUsername());
		user.setPassword(register.getPassword());
		user.setEmail(register.getEmail());

		user.setRole(new Role(register.getRole().getId(), register.getRole().getRoleName()));
		
		adminService.saveUser(user);
		
		logger.info("user "+user.toString());
		
    	return new ResponseEntity<User>(user,HttpStatus.CREATED);
    }
	
}
