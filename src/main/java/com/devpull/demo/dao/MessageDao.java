package com.devpull.demo.dao;

import java.util.List;

import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

public interface MessageDao {
	
	public List<Message> getMessagesFrom(int receiverId);
	
	public Message sendMsgTo(User sender,User receiver,String msgData);
	
	public List<Message> getAllMsgs();
	
}
