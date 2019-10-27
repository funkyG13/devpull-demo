package com.devpull.demo.controller;

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
import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/register")
public class RegisterController {

	public static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private AdminService adminService;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String username, @RequestParam String password, @RequestParam String email,
			@RequestParam String roleName) {

		User user = new User();
//		user.setId(register.getId());
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
//		user.setRole(new Role(register.getRole().getId(), register.getRole().getRoleName()));		
		if (roleName.equals("EMPLOYEE")) {
			adminService.saveUser(user);
			logger.info("user " + user.toString());

		}
		if (roleName.equals("COMPANY")) {
			logger.info("user " + user.toString());
			adminService.saveCompany(user);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

}
