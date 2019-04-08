package com.devpull.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

@Service
public interface MessageService {

	List<Message> getMessageForChat(User user1, User user2);
	
	void createMsg(Message msg);
	
	List<Message> getAllMsgs();
	
}
