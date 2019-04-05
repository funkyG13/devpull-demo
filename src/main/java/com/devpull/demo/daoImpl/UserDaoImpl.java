package com.devpull.demo.daoImpl;


import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devpull.demo.dao.UserDao;
import com.devpull.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	//define Entity Manager
	private EntityManager em; 
	
	//set up construction injection
	@Autowired
	public UserDaoImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<User> findAll() {

		
		//get current hibernate session
		Session curSession = em.unwrap(Session.class);
		
		//create the query
		Query<User> query = curSession.createQuery("from User", User.class);
		
		//execute query
		List<User> users = query.getResultList();
		
		//return results
		return users;
	}

	@Override
	public User getUserById(int id) {

		Session session = em.unwrap(Session.class);
		
		User user = session.get(User.class, id);
		
		return user;
	}

	@Override
	public void saveUser(User user) {

		Session session = em.unwrap(Session.class);
		
		session.save(user);
	}

	@Override
	public void deleteUserById(int id) {
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createQuery("delete from User where id=:userId");
		
		query.setParameter("userId", id);
		
		query.executeUpdate();
		
	}

	@Override
	public boolean userExists(User user) {
		
		return getUserByUsername(user.getUsername()) != null;
	}

	@Override
	public User getUserByUsername(String username) {
		
		List<User> users = findAll();
		for(User user: users) {
			if(user.getUsername().equals(username)) {
				return user;
			}	
		}
		return null;
	}

	@Override
	public void update(User user) {
		
		Session session = em.unwrap(Session.class);
		
		session.merge(user);	
		
//		List<User> users = findAll();
//		for(User us: users) {
//			if(user.getId()== us.getId()) {
//				int index = users.indexOf(us);
//				users.set(index, user);
//			}
		}
	}

	


