package com.devpull.demo.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void testUserGetterAndSetter() {
		final User user = new User();
		user.setEmail("mike@yahoo.com");
		user.setFirstName("mike");
		user.setLastName("Tsir");
		final List<Languages> langList = new ArrayList<>();
		user.setLanguageList(langList);
		user.setPassword("12@!@");
		user.setId(1);
		final List<Message> msgReceived = new ArrayList<>();
		user.setMsgReceiver(msgReceived);
		final List<Message> msgSent = new ArrayList<>();
		user.setMsgSender(msgSent);
		final Role role = new Role();
		user.setRole(role);
		user.setUsername("funkyM");
		final List<PersistentLogins> persistentLogins = new ArrayList<>();
		user.setPersistentLogins(persistentLogins);
		user.toString();
		final User user1 = new User("username","password");
		final User user2 = new User("firstName","lastName","email","userName");
		assertEquals("users langList: ", langList, user.getLanguageList());
		assertEquals("users msgs received: ", msgReceived, user.getMsgReceiver());
		assertEquals("users msgs sent: ", msgSent, user.getMsgSender());
		assertEquals("users role: ", role, user.getRole());
		assertEquals("users persistentLogins: ", persistentLogins, user.getPersistentLogins());
		assertEquals("email: ", "mike@yahoo.com", user.getEmail());
		assertEquals("FirstName: ", "mike", user.getFirstName());
		assertEquals("LastName: ", "Tsir", user.getLastName());
		assertEquals("UserName: ", "funkyM", user.getUsername());
		assertEquals("Password: ", "12@!@", user.getPassword());
		assertEquals("Id: ", 1, user.getId());
	}
}
