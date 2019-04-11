package com.devpull.demo.daoImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devpull.demo.dao.TokenDao;
import com.devpull.demo.dao.UserDao;
import com.devpull.demo.model.PersistentLogins;
import com.devpull.demo.model.User;

@Repository
public class TokenDaoImpl implements TokenDao {

	
	public static final Logger logger = LoggerFactory.getLogger(TokenDaoImpl.class);

	@Autowired
	private UserDao userDao;
	
	private EntityManager em; 
		
	@Autowired
	public TokenDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public String createToken(User user) {
		
	            String uuid = UUID.randomUUID().toString();
	            
	            Session curSession = em.unwrap(Session.class);
	            
	            Query query = curSession.createSQLQuery("insert into persistent_logins values(default,?,?,?)");
	            
//	            Query query = curSession.createSQLQuery("insert into PersistentLogins(id,userId, token)"
//	            								+ "select default, userId, token from PersistentLogins");
//	            query.setParameter(0, default );
	            query.setParameter(1, user.getId());
	            query.setParameter(2, uuid);
	            query.setParameter(3, new Date());
	            
	            query.executeUpdate();
	            
	   		 return uuid;		 
	}

	@Override
	public User getUserOfToken(String token) {
			
	
		int userId = getUserIdOfToken(token);
		
		User user = userDao.getUserById(userId);
		
//		useToken(token);
		
		return user;
	}

	
	private int getUserIdOfToken(String token) {
		
		Session session = em.unwrap(Session.class);
		
		Query<PersistentLogins> query = session.createQuery("from PersistentLogins P where P.token= :token",PersistentLogins.class);
		
		query.setParameter("token", token);
		
		PersistentLogins pl = query.getSingleResult();
		
			int userId= pl.getUserId().getId();
			
			logger.info("userId: +"+userId);			
		
		return userId;
	}
	
	public void useToken(String token) {
		
		Session session = em.unwrap(Session.class);
		
		String sql = "update persistent_logins set last_access_time = default where token = ?";
		
		Query query = session.createSQLQuery(sql);
		
		query.setParameter(1, token);
		
		query.executeUpdate();
		
	}

	@Override
	public String getTokenOfUser(User user) {

		Session session = em.unwrap(Session.class);
		
		
		User user1 = userDao.getUserById(user.getId());
		
		String sql = "select token from persistent_logins where user_id= ?" ;
		
		Query query = session.createSQLQuery(sql);
		
		String token = (String) query.getSingleResult();
		
		return token;
	}
	
}
