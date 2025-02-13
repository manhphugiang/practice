package ca.sheridancollege.giangma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exercise2Controller {
@GetMapping("/")
public String myRoot() {
	return "root.html";
}

@GetMapping("/name")
public String myName() {
	return "giangma.html";
}
@GetMapping("/school")
public String mySchool() {
	return "sheridancollege.html";
}
@GetMapping("/program")
public String myProgram() {
	return "sdne.html";
}
}
