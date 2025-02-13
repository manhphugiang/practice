package ca.sheridancollege.giangma.emails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ca.sheridancollege.giangma.beans.Drink;
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

	public void sendDrinkListEmail(List<Drink> drinks, String to, String subject)
	        throws MessagingException {
	    // Prepare the evaluation context
	    final Context ctx = new Context();
	    ctx.setVariable("tableDrinkContent", getHtmlContent(drinks));
	    
	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	    message.setSubject(subject);
	    message.setTo(to);
	    
	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.templateEngine.process("emailTemplate.html", ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    
	    // Send mail
	    this.emailSender.send(mimeMessage);
	}

	public String getHtmlContent(List<Drink> drinks) {
	    // Create Thymeleaf context
	    Context ctx = new Context();
	    ctx.setVariable("tableDrink", drinks);

	    // Process the Thymeleaf template and return the HTML content
	    return templateEngine.process("viewDrinks.html", ctx);
	}


}
