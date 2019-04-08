package com.devpull.demo.daoImpl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devpull.demo.dao.TokenDao;
import com.devpull.demo.dao.UserDao;
import com.devpull.demo.model.User;

@Repository
public class TokenDaoImpl implements TokenDao {

	private EntityManager em; 
		
	@Autowired
	public TokenDaoImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public String createToken(User user) {
		 try {
	            String uuid = UUID.randomUUID().toString();
	            
	            Session curSession = em.unwrap(Session.class);
	            
	            Query query = curSession.createSQLQuery("insert into persistent_logins values(?,?,?,?)");
	            
	            query.setParameter(0, user.getUsername());
//	            query.setParameter(1, user.getUsername());
	            
		 }catch(Exception e) {
			 
			 
		 }
		 
		 return null;
	}

	@Override
	public User getUserOfToken(String token, UserDao userDao) {
		// TODO Auto-generated method stub
		return null;
	}

}
