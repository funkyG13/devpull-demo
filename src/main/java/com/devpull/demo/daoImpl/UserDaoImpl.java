package com.devpull.demo.daoImpl;


import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devpull.demo.dao.UserDao;
import com.devpull.demo.model.Role;
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

		Session curSession = em.unwrap(Session.class);

		Query<User> query = curSession.createQuery("from User", User.class);

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
		
		}

	@Override
	public Role findByRole(String role) {

		return null;
	}

	@Override
	public List<User> findAllCompanies() {
		
		Session curSession = em.unwrap(Session.class);

		Query<User> query = curSession.createSQLQuery("select user_id from user_role where role_id='3'");

		List<User> users = query.getResultList();		
		
		return users;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getUser(String username, String password) {

		Session session = em.unwrap(Session.class);
		
//		String sql = "select * from user where username=:username AND where password=:password";
//		
//		Query query = session.createNativeQuery(sql);
		
		Query query = session.createQuery("from user u where u.username= :username and u.password= :password",User.class);
		
		query.setParameter("username", username);
		query.setParameter("password", password);
		
//		List<User> results = query.getResultList();
		
		User user = (User) query.getSingleResult();
		
		return user;
	}

//	@Override
//	public boolean validateUser(User user) {
//
//		Session session = em.unwrap(Session.class);
//		
//		String username = user.getUsername();
//		String password = user.getPassword();
//		
//		Query query = session.createQuery("from User where username=: username AND password=: password", User.class);
//		
//		query.setParameter("username", username);
//		query.setParameter("password", password);
//		
//		query.executeUpdate();
//		
//		return false;
//	}
	}

	


