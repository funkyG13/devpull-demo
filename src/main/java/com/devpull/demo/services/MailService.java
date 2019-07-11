package com.devpull.demo.services;

import java.io.IOException;

import javax.mail.MessagingException;

public interface MailService {

	void sendEmail();
	
	void sendEmailWithAttachment() throws MessagingException, IOException;
	
}
