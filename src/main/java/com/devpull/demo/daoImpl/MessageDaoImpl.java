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
import com.devpull.demo.dao.TokenDao;
import com.devpull.demo.dao.UserDao;
import com.devpull.demo.model.Message;
import com.devpull.demo.model.User;

@Repository
public class MessageDaoImpl implements MessageDao {

	public static final Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);
	
	private EntityManager em; 

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TokenDao tokenDao;
	
	@Autowired
	public MessageDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Message> getAllMsgs() {
		
		Session curSession = em.unwrap(Session.class);
		
		Query<Message> query = curSession.createQuery("from Message",Message.class);
		
		List<Message> msgs = query.getResultList();
//		for (Message message : msgs) {
//			
//			int receiverId = message.getReceiverMsg().getId();
//			int senderId = message.getSenderMsg().getId();
//			
//			message = new Message(message.getId(), receiverId, senderId);
//		}
		
		
		return msgs;
	}
//	public List<Message> getMessagesFrom(String token,int contactId) {
	
	@Override
	public List<Message> getMessagesBetween(String token, int receiverId) {

		Session curSession = em.unwrap(Session.class);
		
		
		User receiver = userDao.getUserById(receiverId);
		
		logger.info("receive: "+receiver.toString());
		
		User sender = tokenDao.getUserOfToken(token);
		
		logger.info(sender.toString());
		
//		int senderId = sender.getId();
		
//		logger.info("SenderId: "+senderId);

		Query<Message> query = curSession.createQuery("from Message where receiverMsg=:receiverId and senderMsg=:senderId"
														,Message.class);
		
		query.setParameter("receiverId", receiver);
		query.setParameter("senderId", sender);
		
		List<Message> msgs = query.getResultList();
		
		Query<Message> query1 = curSession.createQuery("from Message where receiverMsg=:senderId and senderMsg=:receiverId"
															,Message.class);
		
		query1.setParameter("receiverId", receiver);
		query1.setParameter("senderId", receiver);
		
		List<Message> msgsReverse = query1.getResultList();
		
		List<Message> mergedList = new ArrayList<Message>();
		mergedList.addAll(msgs);
		mergedList.addAll(msgsReverse);
		
		return mergedList;
		
	}
//		List<Message> msgs = getAllMsgs();
//		List<Message> tempList = new ArrayList<Message>();
//		for (Message message : msgs) {
//			if(receiverId == message.getReceiverMsg().getId()) {
//				logger.info("Message list from receiver");
//				tempList.add(message);
//			}			
//		}
//		
//		return tempList;
//	}
	
	@Override
	public Message sendMsgTo(int senderId, int receiverId, String msgData) {

		Session currentSession = em.unwrap(Session.class);
		
		User sender = userDao.getUserById(senderId);
		
		User receiver = userDao.getUserById(receiverId);
		
		Message msg = new Message();
		
		msg.setSenderMsg(sender);
		msg.setReceiverMsg(receiver);
		msg.setMsgData(msgData);
		
		logger.info("msg info : ");
				
		Query query = currentSession.createSQLQuery("insert into message values(default,?,?,?)");

		query.setParameter(1, senderId);
		query.setParameter(2, receiverId);
		query.setParameter(3, msgData);
		
		query.executeUpdate();
		
		logger.info("message inserted");
		
		logger.info("message info: "+ msg.toString());
		
		return msg;
	}

	
	

}
