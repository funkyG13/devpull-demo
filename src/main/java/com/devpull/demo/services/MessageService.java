package com.devpull.demo.services;

import java.util.List;
import com.devpull.demo.model.Message;

public interface MessageService {

	List<Message> getMessagesBetween(String token, int receiverId);

	Message sendMsgTo(int senderId, int receiverId, String msgData);

	List<Message> getAllMsgs();

}
