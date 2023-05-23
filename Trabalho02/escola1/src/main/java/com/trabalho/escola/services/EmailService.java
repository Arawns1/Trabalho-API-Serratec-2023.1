package com.trabalho.escola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.trabalho.escola.entities.Instrutor;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

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
	
	public EmailService(JavaMailSender javaMailSender){
		this.emailSender = javaMailSender;
	}
	
	
	
	
	public void enviarEmail(String destinatario, String assunto, Instrutor instrutor) throws MessagingException {
		
		/*	SimpleMailMessage mailMessage = new SimpleMailMessage();
		 * mailMessage.setTo(destinatario); mailMessage.setSubject(assunto);
		 * mailMessage.setText(mensagem); mailMessage.setFrom(mailFrom);
		 */
		
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<html>\r\n"
				+ "    <body>\r\n"
				+ "       <h2 style='color:green; text-align:center'> Instrutor cadastrado com Sucesso! </h2>\r\n"
				+ "        <hr/>\r\n"
				+ "        <br/>\r\n"
				+ "        <br/>\r\n"
				+ "        <table style='margin-left: auto; margin-right:auto; border-collapse:collapse; border:1px solid #000000'>\r\n"
				+ "			<tr>"
				+ "		    	<th>ID</th>"
				+ "			    <th>RG</th>"
				+ "			    <th>Nome</th>"
				+ "			    <th>Telefone</th>"
				+ "			 </tr>"
				+ "            <tr>\r\n"
				+ "                <td style='border:1px solid #000000;'>"+instrutor.getIdInstrutor()+"</td>\r\n"
				+ "                <td style='border:1px solid #000000;'>"+instrutor.getRg()+"</td>\r\n"
				+ "                <td style='border:1px solid #000000;'>"+instrutor.getNome()+"</td>\r\n"
				+ "                <td style='border:1px solid #000000;'>"+instrutor.getTelefone()+"</td>\r\n"
				+ "            </tr>\r\n"
				+ "        </table>\r\n"
				+ "    </body>\r\n"
				+ "</html>";
		mimeMessage.setContent(htmlMsg, "text/html");
		helper.setTo(destinatario);
		helper.setSubject(assunto);
		helper.setFrom(mailFrom);
		
		try {
			emailSender.send(mimeMessage);
		}
		catch(Exception e) {
			System.out.println("Ocorreu um erro ao enviar "
					+ "o email! " + e.getMessage());
		}
	}
	
}
