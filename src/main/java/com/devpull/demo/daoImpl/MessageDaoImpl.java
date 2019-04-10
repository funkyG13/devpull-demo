package com.devpull.demo.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devpull.demo.dao.MessageDao;
import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

@Repository
public class MessageDaoImpl implements MessageDao {

	public static final Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);
	
	private EntityManager em; 

	@Autowired
	public MessageDaoImpl(EntityManager em) {
		this.em = em;
	}



	@Override
	public List<Message> getAllMsgs() {
		
		Session curSession = em.unwrap(Session.class);
		
		Query<Message> query = curSession.createQuery("from Message",Message.class);
		
		
		
		List<Message> msgs = query.getResultList();
		
		return msgs;
	}

	@Override
	public List<Message> getMessagesFrom(int receiverId) {

//		Session curSession = em.unwrap(Session.class);

		List<Message> msgs = getAllMsgs();
		List<Message> tempList = new ArrayList<Message>();
		for (Message message : msgs) {
			if(receiverId == message.getReceiver().getId()) {}
			tempList.add(message);
		}
		
		return tempList;
	}



	@Override
	public Message sendMsgTo(User sender, User receiver, String msgData) {

		Session currentSession = em.unwrap(Session.class);
		
		Query query = currentSession.createSQLQuery("insert into message values(default,?,?,?)");
		
//		sender = currentSession.get(User.class, id);
		
		query.setParameter(1, sender);
		query.setParameter(2, receiver);
		query.setParameter(3, msgData);
		
		query.executeUpdate();
		
		Message msg = new Message(sender,receiver,msgData);
		
		logger.info("message info: "+ msg.toString());
		
		return msg;
	}


}
