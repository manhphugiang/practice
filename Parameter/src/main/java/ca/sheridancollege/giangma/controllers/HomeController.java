package ca.sheridancollege.giangma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HomeController {
@GetMapping("/")
public String myRoot() {
	return "root.html";
}

@GetMapping("/petInformation")
public String addPetInfo(@RequestParam String name, @RequestParam int age, 
						@RequestParam String type, @RequestParam double price, 
						@RequestParam(required=false, defaultValue="false") Boolean trained) {
System.out.println("Name: " + name + " " + "  Age: " + age + " " + "Type: " + type + "  Price: " + price + "  Trained:" + trained);
return "root.html";
}
}

