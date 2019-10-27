package com.devpull.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;

import com.devpull.demo.model.CustomMessage;
import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;
import com.devpull.demo.services.AdminService;

import com.devpull.demo.services.MessageService;
import com.devpull.demo.services.TokenService;
//import com.devpull.demo.util.CustomErrorType;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/messages")
public class MessageController {

	public static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService msgService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private AdminService adminService;

	@GetMapping("/list_all_msgs")
	public ResponseEntity<List<Message>> showAllMessages() {
		logger.info("Fetching Msg List");
		List<Message> msgs = msgService.getAllMsgs();
		if (msgs.isEmpty()) {
			return new ResponseEntity<>(msgs, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Message>>(msgs, HttpStatus.OK);
	}

	@PostMapping("/send_msg")
	public ResponseEntity<Void> sendMsgTo(@RequestParam int senderId, @RequestParam int receiverId,
			@RequestParam String text) {

		logger.info("sender: " + senderId + "receiver: " + receiverId + "text: " + text);
//		User user = tokenService.getUserOfToken(token);

		User sender = adminService.getUserById(senderId);
		User receiver = adminService.getUserById(receiverId);

		if (!(adminService.userExists(receiver))) {
			logger.error("Unable to create Message.");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		Message msg = msgService.sendMsgTo(senderId, receiverId, text);
		logger.info("msg created " + msg.toString());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/get_messages_between")
	public ResponseEntity<List<CustomMessage>> getMsgsBetween(@RequestParam String token,
			@RequestParam int receiverId) {

		logger.info("Getting msgs from receiver with id {}", receiverId);
		logger.info("Getting msgs from token {}", token);
		User sender = tokenService.getUserOfToken(token);
		logger.info("sender to string: " + sender.toString());

		List<Message> msgs = msgService.getMessagesBetween(token, receiverId);
		List<CustomMessage> customMsgs = new ArrayList<CustomMessage>();
		CustomMessage cm = new CustomMessage();
		for (Message message : msgs) {
			logger.info(cm.toString());
			customMsgs.add(new CustomMessage(message.getId(), receiverId, message.getSenderMsg().getId(),
					message.getMsgData()));
		}

		logger.info("customMsgs " + customMsgs.toString());
		logger.info("Custom Msgs Size" + customMsgs.size());
		if (msgs == null || msgs.isEmpty()) {
			return new ResponseEntity<List<CustomMessage>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CustomMessage>>(customMsgs, HttpStatus.OK);
	}

}
