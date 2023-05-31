package com.residencia.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.exception.CustomException;

import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
    @Autowired
    public JavaMailSender emailSender;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private String mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${mail.from}")
    private String mailFrom;

    public MailService(JavaMailSender javaMailSender){
        this.emailSender = javaMailSender;
    }

    public void enviarEmail(String destinatario, String assunto, String htmlMsg) throws MessagingException {
    	MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        try {
			/* mimeMessage.setContent(htmlMsg, "text/html"); */
        	helper.setText("a",htmlMsg);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
			helper.setFrom(mailFrom);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		/*
		 * SimpleMailMessage mailMessage = new SimpleMailMessage();
		 * mailMessage.setTo(destinatario); mailMessage.setSubject(assunto);
		 * mailMessage.setText(pedidoDTO.toString()); mailMessage.setFrom(mailFrom);
		 */
            try {
				 emailSender.send(mimeMessage); 
				/* Transport.send(mimeMessage); */
            } catch (Exception e) {
                throw new CustomException("Ocorreu um erro ao enviar " + "o email! " + e.getMessage());
            }
    }
}