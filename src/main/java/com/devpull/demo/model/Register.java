package com.devpull.demo.model;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Register {

    private int id;
    @NotEmpty(message="Please provide your first name!")
    private String firstName;
    @NotEmpty(message="Please provide your last name!")
    private String lastName;
    @NotEmpty(message="Please provide your email!")
    @Pattern(regexp="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$" , message="Prodive a correct email")
    private String email;
    @NotEmpty(message="Please provide a username!")
    private String username;
    @NotEmpty(message="Please provide a password!")
    private String password;
    private String roleName;
    
	public Register() {
	}
	
	public Register(int id, @NotEmpty(message = "Please provide your first name!") String firstName,
			@NotEmpty(message = "Please provide your last name!") String lastName,
			@NotEmpty(message = "Please provide your email!") @Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", message = "Prodive a correct email") String email,
			@NotEmpty(message = "Please provide a username!") String username,
			@NotEmpty(message = "Please provide a password!") String password, String roleName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roleName = roleName;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
  
}
