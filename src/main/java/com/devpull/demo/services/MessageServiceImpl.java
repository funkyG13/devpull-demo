package com.devpull.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devpull.demo.dao.MessageDao;
import com.devpull.demo.model.Message;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao msgDao;

	@Override
	@Transactional
	public List<Message> getAllMsgs() {
		return msgDao.getAllMsgs();
	}

	@Override
	@Transactional
	public List<Message> getMessagesBetween(String token, int receiverId) {
		return msgDao.getMessagesBetween(token,receiverId);
	}

	@Override
	@Transactional
	public Message sendMsgTo(int senderId, int receiverId, String msgData) {
		return msgDao.sendMsgTo(senderId, receiverId, msgData);
	}

}
