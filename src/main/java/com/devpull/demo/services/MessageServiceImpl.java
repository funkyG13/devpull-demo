package com.devpull.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devpull.demo.dao.MessageDao;
import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao msgDao;
	

	@Override
	@Transactional
	public void createMsg(Message msg) {

		msgDao.createMsg(msg);
	}

	@Override
	@Transactional
	public List<Message> getAllMsgs() {
		return msgDao.getAllMsgs();
	}

	@Override
	@Transactional
	public List<Message> getMessagesFrom(int receiverId) {
		return msgDao.getMessagesFrom(receiverId);
	}

	@Override
	@Transactional
	public void sendMsgTo(Message msg, User user) {
			msgDao.sendMsgTo(msg, user);		
	}

}
