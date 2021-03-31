package com.avantetech.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.avantetech.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail()); // pra qm vai ser enviado
		sm.setFrom(sender); // de qual email estou enviando
		sm.setSubject("Pedido confirmado! Código: "+ obj.getId()); // asunto do email
		sm.setSentDate(new Date(System.currentTimeMillis())); // data do email
		sm.setText(obj.toString()); // corpo do email
		return sm;
	}
	
}
