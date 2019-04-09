package com.devpull.demo.dao;

import java.util.List;

import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

public interface MessageDao {


	
	public List<Message> getMessagesFrom(int receiverId);
	
	public void createMsg(Message msg);
	
	public void sendMsgTo(Message msg,User user);
	
	public List<Message> getAllMsgs();
	
}
