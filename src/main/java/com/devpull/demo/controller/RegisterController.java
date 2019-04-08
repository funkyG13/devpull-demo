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
	
//	@PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody User user) {
//   		user.setRoles(Arrays.asList(new UserRole("USER")));
//    	User newUser = adminService.addUser(user);
//    	return new ResponseEntity<>(HttpStatus.CREATED);
//    }
	
}
