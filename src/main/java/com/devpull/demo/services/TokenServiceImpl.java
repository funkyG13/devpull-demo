package com.devpull.demo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devpull.demo.dao.TokenDao;
import com.devpull.demo.model.User;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenDao tokenDao;
	
	@Override
	@Transactional
	public String createToken(User user) {
		return tokenDao.createToken(user);
	}

	@Override
	@Transactional
	public User getUserOfToken(String token) {
		return tokenDao.getUserOfToken(token);
	}

}
