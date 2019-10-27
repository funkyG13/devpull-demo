package com.devpull.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devpull.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findUserById(int id);
	
}
