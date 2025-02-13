package ca.sheridancollege.ngquocth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class homecontroller {

	@GetMapping("/")
	public String root() {
		return "home.html";
	}
	
	
	
	@GetMapping("/user")
	public String user() {
		return "user.html";
	}	
	
	
	
	@GetMapping("/pickle")
	public String pickle() {
		return "pickle.html";
	}		
	

	
}
