package com.devpull.demo.converters;

import org.springframework.core.convert.converter.Converter;

import com.devpull.demo.model.Register;
import com.devpull.demo.model.User;

public class RegisterFormToUser implements Converter<Register, User>{

	@Override
	public User convert(Register registerForm) {

		User user = new User();
		
		
		
		return user;
	}

}
