package com.devpull.demo.daoImpl;

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

	@Autowired
	private EntityManager em; 
	
	@Override
	public List<Message> getMessageForChat(User user1, User user2) {

		Session curSession = em.unwrap(Session.class);
		
		Query<Message> query = curSession.createQuery("from Message where senderId=:sender AND receiverId=:receiver",
														Message.class);
		
		query.setParameter("sender", user1);	
		query.setParameter("receiver", user2);
		
		List<Message> msgs = query.getResultList();
		
		return msgs;
	}

	@Override
	public void createMsg(Message msg) {

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

}
