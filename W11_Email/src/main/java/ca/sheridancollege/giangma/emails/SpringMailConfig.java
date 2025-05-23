package ca.sheridancollege.giangma.emails;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import ca.sheridancollege.giangma.controller.Page;
import ca.sheridancollege.giangma.controller.a;

@Configuration
public class SpringMailConfig {
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("gmanhphuu.study@gmail.com");
		mailSender.setPassword("ejslhwcpgxzayxjb");
		
		
		Properties props = mailSender.getJavaMailProperties();
		
		
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		return mailSender;
	}
	
}
