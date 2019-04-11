package com.devpull.demo.dao;

import java.util.List;

import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

public interface MessageDao {
	
	public List<Message> getMessagesFrom(int receiverId);
	
	public Message sendMsgTo(int senderId,int receiverId,String msgData);
	
	public List<Message> getAllMsgs();
	
}
