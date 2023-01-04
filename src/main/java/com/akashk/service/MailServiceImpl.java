package com.akashk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String sendPasswordMail(String to,String password,String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setFrom("akashk33@hotmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(" password ");
		simpleMailMessage.setText(body);
		
		javaMailSender.send(simpleMailMessage);
		
		return " please check email for password ";
	}
	
}
