package com.avantetech.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.avantetech.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
