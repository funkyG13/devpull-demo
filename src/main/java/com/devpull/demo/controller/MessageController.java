package com.devpull.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;

import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;

import com.devpull.demo.services.MessageService;
//import com.devpull.demo.util.CustomErrorType;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private MessageService msgService;

	@Autowired
	private AdminService adminService;

	
	@GetMapping("/list_all_msgs")	
	public ResponseEntity<List<Message>> showAllMessages(){
		logger.info("Fetching Msg List");
		
		List<Message> msgs = msgService.getAllMsgs();
				
		if(msgs.isEmpty()) {
			return new ResponseEntity<>(msgs , HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Message>>(msgs, HttpStatus.OK);

	}

	@PostMapping("/send_msg")
	public ResponseEntity<Void> sendMsgTo(@RequestBody Message msg, User user){

		logger.info("Creating Msg " + msg.getMsgData());

//		user = adminService.getUserByUsername();
		
		if (msg.getReceiver() == null) {
			logger.error("Unable to create Message.");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		msgService.sendMsgTo(msg, user);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
//	@GetMapping("/get_messages_betweet_users/{receiverId}")
//	public ResponseEntity<List<Message>> getMsgsBetweenUsers(@RequestBody Message msg,@RequestBody User user1,
//														@RequestBody User user2, @PathVariable int receiverId){
//		
//		logger.info("Getting Msgs Between user1 & user2 " );
//		
//		int senderId = user1.getId();
//		user1.setId(senderId);
//		user1 = adminService.getUserById(senderId);
//		
//		user2 = adminService.getUserById(receiverId);
//		
//		if(user1 == null || user2 == null) {
//			logger.error("Cannot find user1 or user2");
//			return new ResponseEntity<List<Message>>(new CustomErrorType("User1 with id"+user1.getId()+
//														" or User 2 "+user2.getId()+"are null"), HttpStatus.BAD_REQUEST);
//			
//		}
//		
//		List<Message> msgs = msgService.getMessageForChat(user1, user2);
//		
//		return new ResponseEntity<List<Message>>(msgs, HttpStatus.OK);
//
//	}

	@GetMapping("/get_messages_from/{receiverId}")
	public ResponseEntity<List<Message>> getMsgsFromUser(@PathVariable int receiverId){
		
		logger.info("Getting msgs from {}", receiverId);
		List<Message> msgs = msgService.getMessagesFrom(receiverId);
		
		if (msgs == null || msgs.isEmpty()) {
			return new ResponseEntity<List<Message>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Message>>(msgs, HttpStatus.OK);
	}
	
}

