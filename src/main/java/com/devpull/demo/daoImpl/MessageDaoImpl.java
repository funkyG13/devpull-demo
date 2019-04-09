package com.devpull.demo.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devpull.demo.dao.MessageDao;
import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

@Repository
public class MessageDaoImpl implements MessageDao {

	
	private EntityManager em; 

	@Autowired
	public MessageDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void sendMsgTo(Message msg, User user) {

		Session curSession = em.unwrap(Session.class);
		
		curSession.save(msg);
		
	}

	@Override
	public List<Message> getAllMsgs() {
		
		Session curSession = em.unwrap(Session.class);
		
		Query<Message> query = curSession.createQuery("from Message",
														Message.class);
		List<Message> msgs = query.getResultList();
		
		return msgs;
	}

	@Override
	public List<Message> getMessagesFrom(int receiverId) {

		Session curSession = em.unwrap(Session.class);

		List<Message> msgs = getAllMsgs();
		List<Message> tempList = new ArrayList<Message>();
		for (Message message : msgs) {
			if(receiverId == message.getReceiver().getId()) {}
			tempList.add(message);
		}
		
		return tempList;
	}

	@Override
	public void createMsg(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
