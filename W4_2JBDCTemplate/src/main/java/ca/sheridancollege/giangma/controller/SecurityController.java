package ca.sheridancollege.giangma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
	@GetMapping("/login")
	public String loginRoot() {
		return "login.html";
	}
	
	@GetMapping ("/access-denied")
	public String ad() {
		return "access-denied.html";
	}
	
}
