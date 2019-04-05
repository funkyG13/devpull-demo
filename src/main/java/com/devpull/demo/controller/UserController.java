package com.devpull.demo.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;
import com.devpull.demo.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private AdminService adminService;
	
	@Autowired
	public UserController(AdminService adminService) {
		this.adminService = adminService;
	}

	//returns list of users at "/users"
	@GetMapping("/users")
	public ResponseEntity<List<User>>  users(){
		
		List<User> users = adminService.findAll();
		
		if(users.isEmpty()) {
			return new ResponseEntity<>(users , HttpStatus.NOT_FOUND);
		}
		
	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> selectUser(@PathVariable int userId) {
		
		logger.info("Fetching User by id", userId);
		User user =  adminService.getUserById(userId);
				
		if(user == null) {
			logger.error("User id was not found", userId);
			return new ResponseEntity<User>(new CustomErrorType("There is no user with id :"+userId), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		
		logger.info("Adding User "+ user);
		
		if (adminService.userExists(user)) {
			logger.error("Unable to create user with username: "+ user.getUsername(), user.getUsername());
			return new ResponseEntity<Void>(new CustomErrorType("Unable to create user with username: "+
												user.getUsername()),HttpStatus.CONFLICT );
		}
		adminService.saveUser(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable int userId,@RequestBody User user) {
		
		logger.info("Updating user with id {}", userId);
		
		User currUser = adminService.getUserById(userId);
		
		if(currUser == null) {
			logger.error("User with id {} not found.", userId);
			return new ResponseEntity<User>(new CustomErrorType("User not found: "+ user.getUsername()),HttpStatus.NOT_FOUND);
		}
		
		currUser.setId(user.getId());
		currUser.setFirstName(user.getFirstName());
		currUser.setLastName(user.getLastName());
		currUser.setUsername(user.getUsername());
		currUser.setPassword(user.getPassword());
		currUser.setEmail(user.getEmail());
		currUser.setAboutMe(user.getAboutMe());
		currUser.setUserCv(user.getUserCv());
		currUser.setJobDescr(user.getJobDescr());
		
		adminService.update(user);
		
		return new ResponseEntity<User>(currUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
		
	logger.info("Deleting user with id: "+ userId);	
	User user = adminService.getUserById(userId);
		
	if(user == null) {
		logger.error("User not found.Unable to delete user with id :" +userId);
		return new ResponseEntity<Void>(new CustomErrorType("User with id"+userId+" not found. "),HttpStatus.NOT_FOUND);
	}
	
	adminService.deleteUserById(userId);
	
	return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
