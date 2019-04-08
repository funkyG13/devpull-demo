package com.devpull.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devpull.demo.model.Message;
//import com.devpull.demo.model.User;
//import com.devpull.demo.services.AdminService;
import com.devpull.demo.services.MessageService;
//import com.devpull.demo.util.CustomErrorType;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private MessageService msgService;

//	@Autowired
//	private AdminService adminService;

	@GetMapping("/list_all_msgs")	
	public ResponseEntity<List<Message>> showAllMessages(){
		logger.info("Fetching Msg List");
		
		List<Message> msgs = msgService.getAllMsgs();
				
		if(msgs.isEmpty()) {
			return new ResponseEntity<>(msgs , HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Message>>(msgs, HttpStatus.OK);

	}

	@PostMapping("/msg")
	public ResponseEntity<Void> sendMsg(@RequestBody Message msg, UriComponentsBuilder ucBuilder) {

		logger.info("Creating Msg " + msg.getMsgData());

		if (msg.getReceiverId() == 0) {
			logger.error("Unable to create Message.");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		msgService.createMsg(msg);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/msg/{id}").buildAndExpand(msg.getId()).toUri());

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
