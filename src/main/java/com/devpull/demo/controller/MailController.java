package com.devpull.demo.controller;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devpull.demo.services.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;

//	@PostMapping
//	public void sendFeedback(@RequestBody Feedback feedback,
//								BindingResult bindingResult) {
//		
//		if(bindingResult.hasErrors()) {
//			throw new ValidationException("not valid");
//		}
//		
//		mailSender.setHost(this.emailConfig.getHost());
//		System.out.println(this.emailConfig.getHost());
//		
//		mailSender.setPort(this.emailConfig.getPort());
//		System.out.println(this.emailConfig.getPort());
//
//		
//		
//		mailSender.setUsername(this.emailConfig.getUsername());
//		System.out.println(this.emailConfig.getUsername());
//
//		mailSender.setPassword(this.emailConfig.getPassword());
//		
//		//create email instance
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setFrom("m.tsiroglou13@gmail.com");
//		mailMessage.setTo("m.tsiroglou@hotmail.com");
//		mailMessage.setText("hiiiiii");
//		mailMessage.setSubject("Mail Practise with Spring");
//		System.out.println(mailMessage.getText()); 
//		
//		//send Mail
//		mailSender.send(mailMessage);
//	
//	}

	@GetMapping("/send")
	public ResponseEntity<String> sendEmail(){
		
		System.out.println("Sending Mail...");
		mailService.sendEmail();
		System.out.println("Done...");
		return new ResponseEntity<String>("Mail was sent", HttpStatus.OK);
	}

}
