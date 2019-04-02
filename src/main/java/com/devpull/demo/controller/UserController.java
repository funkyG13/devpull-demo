package com.devpull.demo.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devpull.demo.dao.UserDao;
import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;

@RestController
@RequestMapping("/api")
public class UserController {

	private AdminService adminService;
	
	@Autowired
	public UserController(AdminService adminService) {
		this.adminService = adminService;
	}

	//returns list of users at "/users"
	@GetMapping("/users")
	public List<User> users(){
		return adminService.findAll();
	}
	
	
	@GetMapping("/users/{userId}")
	public User selectUser(@PathVariable int userId) {
		User user =  adminService.getUserById(userId);
		
		if(user == null) {
			throw new RuntimeException("User id not found: "+ userId);
		}
		return user;
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		
		//set id to 0 in order to force a save of a new item instead of update
		user.setId(0);
		
		adminService.saveUser(user);;
		 
		return user;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		
		adminService.saveUser(user);
		
		return user;
		
	}
	
	@DeleteMapping("users/{userId}")
	public String deleteUser(@PathVariable int userId) {
		
	User user = adminService.getUserById(userId);
		
	if(user == null) {
		throw new RuntimeException( "The user with id "+ userId+ " doesn't exist.");
	}
	
	adminService.deleteUserById(userId);
	
	return "User with id:"+ userId + " deleted";
	}
	
}
