package com.devpull.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

@Service
public interface MessageService {

	
	List<Message> getMessagesFrom(int receiverId);
	
	void createMsg(Message msg);
	
	void sendMsgTo(Message msg, int receiverId);
	
	List<Message> getAllMsgs();
	
}
