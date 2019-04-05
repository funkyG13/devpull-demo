package com.devpull.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devpull.demo.dao.UserDao;
import com.devpull.demo.model.User;

@Service
public class AdminServiceImpl implements AdminService {

	
	private UserDao userDao;
	
	@Autowired
	public AdminServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}


	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	@Transactional
	public void deleteUserById(int id) {
		userDao.deleteUserById(id);
	}

	@Override
	@Transactional
	public boolean userExists(User user) {
		
		return userDao.userExists(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

}
