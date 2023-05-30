package com.residencia.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.exception.CustomException;

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

    public void enviarEmail(String destinatario, String assunto, PedidoDTO pedidoDTO) {

          SimpleMailMessage mailMessage = new SimpleMailMessage();
          mailMessage.setTo(destinatario);
          mailMessage.setSubject(assunto);
          mailMessage.setText(pedidoDTO.toString());
          mailMessage.setFrom(mailFrom);
            try {
                emailSender.send(mailMessage);
            } catch (Exception e) {
                throw new CustomException("Ocorreu um erro ao enviar " + "o email! " + e.getMessage());
            }
    }
}