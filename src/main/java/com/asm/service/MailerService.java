package com.asm.service;

import javax.mail.MessagingException;

import com.asm.domain.MailInfo;



public interface MailerService {
	void send (MailInfo mail) throws MessagingException;
	void send (String from, String subject, String body) throws MessagingException;
	void queue (MailInfo mail);
	void queue (String to, String subject, String body);
}
