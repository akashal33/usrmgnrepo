package com.akashk.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender mailSender;
	
	public String sendMail(String to,String subject,String body) throws MessagingException {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setFrom("akashk33@hotmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		
		mailSender.send(mimeMessage);
		
		
		return " please check email "  ;
	}

}
