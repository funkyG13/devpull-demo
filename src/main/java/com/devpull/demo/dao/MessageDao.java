package com.devpull.demo.dao;

import java.util.List;

import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

public interface MessageDao {

	public List<Message> getMessageForChat(User user1, User user2);
	
	public void createMsg(Message msg);
	
	public List<Message> getAllMsgs();
	
}
