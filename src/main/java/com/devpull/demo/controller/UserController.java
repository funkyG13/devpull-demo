package com.devpull.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/users")
	public List<User> users(){
		return adminService.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public User selectUser(int id) {
		return adminService.getUserById(id);
	}
	
	public void saveUser(User user) {
		
		adminService.saveUser(user);
	}
	
}
