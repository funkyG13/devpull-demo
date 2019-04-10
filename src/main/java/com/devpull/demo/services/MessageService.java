package com.devpull.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

@Service
public interface MessageService {

	
	List<Message> getMessagesFrom(int receiverId);
	
	Message sendMsgTo(User sender,User receiver,String msgData);
	
	List<Message> getAllMsgs();
	
}
