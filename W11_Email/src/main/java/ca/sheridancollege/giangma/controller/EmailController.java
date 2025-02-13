package ca.sheridancollege.giangma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.giangma.emails.EmailServiceImpl;
import jakarta.mail.MessagingException;

@Controller
public class EmailController {
	@Autowired
	private EmailServiceImpl esi;
	
@GetMapping("/")
public String rootToot() {
	
	// esi.sendSimpleMessage("java2024.sheridan@gmail.com", "Woof", "fgreqgregfwefwe");
	
	try {
	esi.sendMailWithInLine("keduhanhtg@gmail.com",
			"ANH YEU EMMMMMMMM", 
			"Nguyen Duong Phuong Nghi",
			"this is the message body",
			"NOI NGAN LAN CUNG NHU THE");
	} 
	catch(MessagingException e){
		System.out.print(e);
		e.printStackTrace();
		}
	
	return "root.html";
	}



@GetMapping ("/test2")
public String test2() {
	try {
		esi.sendMailWithInLine("icebear422004@gmail.com",
				"Testing 2",
				"what the hell is the name",
				"messsage body", 
				"footer");
		
	}catch(MessagingException e){
		System.out.print(e);
		
	}
	
	return "root.html";
}




}
