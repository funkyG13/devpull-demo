package com.devpull.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("/welcome")
	public String home() {
		
		return "Hello";
	}
	
}
