package com.devpull.demo.services;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("m.tsiroglou13@gmail.com", "m.tsiroglou13@yahoo.com");

        msg.setSubject("Test Mail");
        msg.setText("Hello Mike \n Spring Boot Email");

        javaMailSender.send(msg);

    }
	@Override
    public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("m.tsiroglou13@gmail.com");

        helper.setSubject("Test Mail");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }

}
