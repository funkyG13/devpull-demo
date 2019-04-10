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
import org.springframework.web.bind.annotation.RequestParam;
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

	public static final Logger logger = LoggerFactory.getLogger(MessageController.class);

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

//	@PostMapping("/send_msg")
//	public ResponseEntity<Void> sendMsgTo(@RequestBody Message msg, User user){
//
//		logger.info("Creating Msg " + msg.getMsgData() + " sender: "+msg.getSender()+ " receiver: "+ msg.getReceiver());
//
//		logger.info("receiver: " + user.getReceiver() + " sender: "+user.getSender() );
//		
//		 user = adminService.getUserById(user.getId());
//		 logger.info("user "+ user.toString());
//		
//		if (msg.getReceiver() == null) {
//			logger.error("Unable to create Message.");
//			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//		}
//		msgService.sendMsgTo(msg.getSender(), msg.getReceiver(), msg.getMsgData());
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
	
	
	@PostMapping("/send_msg")
	public ResponseEntity<Message> sendMsgTo(@RequestParam User sender,
			@RequestParam User receiver, @RequestParam String msgData){


		logger.info("receiver: " + receiver.getReceiver() + " sender: "+sender.getSender() );
		
		 sender = adminService.getUserById(sender.getId());
		 logger.info("senderId "+ sender.getId());
		
//		if (receiver == null) {
//			logger.error("Unable to create Message.");
//			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//		}
		Message msg = msgService.sendMsgTo(sender, receiver, msgData);
		
		return new ResponseEntity<Message>(msg,HttpStatus.OK);
	}

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

