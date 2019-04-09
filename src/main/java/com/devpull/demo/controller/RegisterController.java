package com.devpull.demo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody User user) {
		
		user.setId(user.getId());
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setUsername(user.getUsername());
		user.setPassword(user.getPassword());
		user.setEmail(user.getEmail());
		
		
		
    	return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
}
