package com.devpull.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.devpull.demo.model.Languages;
import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;
import com.devpull.demo.services.ExcelService;
import com.devpull.demo.util.CustomErrorType;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private AdminService adminService;
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	public UserController(AdminService adminService) {
		this.adminService = adminService;
	}


	@GetMapping("/users")
	public ResponseEntity<List<User>>  users() throws IOException{
		
		List<User> users = adminService.findAll();		
		if(users.isEmpty()) {
			return new ResponseEntity<>(users , HttpStatus.NOT_FOUND);
		}
		//generate Excel file with all user - info
		excelService.generateUsersExcel();
		
	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> selectUser(@PathVariable @Min(1) int userId) {
		
		logger.info("Fetching User by id", userId);
		User user =  adminService.getUserById(userId);
				
		if(user == null) {
			logger.error("User id was not found", userId);
			return new ResponseEntity<CustomErrorType>((new CustomErrorType("There is no user with id :"+userId, 400, new Date())), HttpStatus.BAD_REQUEST);
			

		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
		
		logger.info("Adding User "+ user);
		
		if (adminService.userExists(user)) {
			logger.error("Unable to create user with username: {}", user.getUsername());
			
			return new ResponseEntity<CustomErrorType>((new CustomErrorType("Unable to create user with username: "+
					user.getUsername(), 400, new Date())), HttpStatus.BAD_REQUEST);
			

		}
		
		if(user.getRole().getRoleName().contentEquals("EMPLOYEE")) {
		adminService.saveUser(user);
		}
		if(user.getRole().getRoleName().contentEquals("COMPANY")) {
			adminService.saveCompany(user);
			}
		
		logger.info("user "+user.getUsername() );
		
		    return new ResponseEntity<User>( HttpStatus.CREATED);

	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable @Min(1) int userId,@RequestBody User user) {
		
		logger.info("Updating user with id {}", userId);
		
		User currUser = adminService.getUserById(userId);
		
		if(currUser == null) {
			logger.error("User with id {} not found.", userId);
			return new ResponseEntity<CustomErrorType>((new CustomErrorType("User not found: "+ user.getUsername(), 400, new Date())), HttpStatus.BAD_REQUEST);
		}		
		currUser.setId(user.getId());
		currUser.setFirstName(user.getFirstName());
		currUser.setLastName(user.getLastName());
		currUser.setUsername(user.getUsername());
		currUser.setPassword(user.getPassword());
		currUser.setEmail(user.getEmail());	
		adminService.update(user);		
		return new ResponseEntity<User>(currUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) {
		
	logger.info("Deleting user with id: "+ userId);	
	User user = adminService.getUserById(userId);
		
	if(user == null) {
		logger.error("User not found.Unable to delete user with id :" +userId);
		return new ResponseEntity<CustomErrorType>(new CustomErrorType("User with id "+userId+" not found. ", 400, new Date()),HttpStatus.NOT_FOUND);
	}
	
	adminService.deleteUserById(userId);
	
	return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/profile/companies")
	public ResponseEntity<List<User>> showAllCompanies(){
		
		List<User> users = adminService.findAllCompanies();		
		if(users.isEmpty()) {
			return new ResponseEntity<>(users , HttpStatus.NOT_FOUND);
		}
		
	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/profile/developers")
	public ResponseEntity<List<User>> showAllDevelopers(){
				
		List<User> users = adminService.findAllUsers();	
		if(users.isEmpty()) {
			return new ResponseEntity<>(users , HttpStatus.NOT_FOUND);
		}
		
	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/profile/developers/languages")
	public ResponseEntity<List<Languages>> showAllLanguages(){
		
		List<Languages> languages = adminService.getAllLanguages();	
		if(languages.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.OK);
	
		return new ResponseEntity<List<Languages>>( languages ,HttpStatus.OK);
	}

	@GetMapping("/profile/developers/{userId}/languages")
	public ResponseEntity<List<Languages>> showUsersLanguages(@PathVariable @Min(1) int userId){
		
		User user = adminService.getUserById(userId);
		
		List<Languages> usersLang = adminService.getUsersLanguage(user);
		
		if(usersLang.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	
		return new ResponseEntity<List<Languages>>( usersLang ,HttpStatus.OK);
	}
	

	
	
}
