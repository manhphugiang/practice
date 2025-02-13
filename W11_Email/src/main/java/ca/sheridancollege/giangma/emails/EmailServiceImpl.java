package ca.sheridancollege.giangma.emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl {
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		emailSender.send(message);
		
		
		
	}
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public void sendMailWithInLine(String to, String subject, String name, 
			String messageBody, String footer) throws MessagingException{
		
		// Import import org.thymeleaf.context.Context;
		// replaces the model attributes
		
		Context ctx = new Context();
		
		ctx.setVariable("name", name);
		ctx.setVariable("message", messageBody);
		ctx.setVariable("footer", footer);;
		
		// create the body of our email using the thymeleaf page and context
		
		String htmlContent = this.templateEngine.process("emailTemplate.html", ctx);
		
				
		// prepare our Email for sending 
				MimeMessage mineMessage = this.emailSender.createMimeMessage();
		
		MimeMessageHelper message =
				new MimeMessageHelper(mineMessage, true, "UTF-8");
	
		message.setTo(to);
		message.setSubject(subject);
		message.setText(htmlContent, true); // true = isHTML
		
		this.emailSender.send(mineMessage);
	}
	
	
}
