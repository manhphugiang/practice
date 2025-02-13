package ca.sheridancollege.giangma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	//root directory or root page 
	@GetMapping("/") // localhost:8080
	
	public String rootPage() {
		return "root.html";
	}

	
	@GetMapping("/name")
	public String myGiangMa() {
		return "giangma.html";
	}
	@GetMapping("/program")
	public String myProgram() {
		return "sdne.html";
	}
	@GetMapping("/school")
	public String mySchool() {
		return "sheridancollege.html";
	}
}
