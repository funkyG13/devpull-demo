package com.devpull.demo.daoImpl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devpull.demo.controller.LoginController;
import com.devpull.demo.dao.UserDao;
import com.devpull.demo.model.Role;
import com.devpull.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private EntityManager em; 
	
	//set up construction injection
	@Autowired
	public UserDaoImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<User> findAll() {

		Session curSession = em.unwrap(Session.class);

		Query<User> query = curSession.createQuery("from User", User.class);

		List<User> users = query.getResultList();

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
		
		int id =2;
		Role role = session.get(Role.class, id);
		
		user.setRole(role);
		
		session.save(user);
	}
	
	@Override
	public void saveCompany(User user) {

		Session session = em.unwrap(Session.class);
		
		int id = 3;
		Role role = session.get(Role.class, id);
		
		user.setRole(role);
		
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
		
		}

	@Override
	public Role findByRole(String role) {

		return null;
	}

	@Override
	public List<User> findAllCompanies() {
		
		Session curSession = em.unwrap(Session.class);

		Query<User> query = curSession.createQuery("from User U where U.role = 3", User.class);

		List<User> users = query.getResultList();		
		
		return users;
	}

	@Override
	public List<User> findAllUsers() {

		Session curSession = em.unwrap(Session.class);

		Query<User> query = curSession.createQuery("from User U where U.role = 2", User.class);
		
		List<User> users = query.getResultList();
		
		return users;
	}

	@Override
	public User getUser(String username, String password) {


		List<User> users = findAll();
		for(User user: users) {
			if(user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {

				logger.info("o user einai : "+user.toString());

				
				return user;
			}
		}

		return null;
		
	}

	}

	


