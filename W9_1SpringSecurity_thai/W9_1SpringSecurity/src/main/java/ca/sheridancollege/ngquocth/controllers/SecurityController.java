package ca.sheridancollege.ngquocth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	
	@GetMapping ("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping ("/access-denied")
	public String ad() {
		return "access-denied.html";
	}
	
	
	
	
	
	
}
