package ca.sheridancollege.giangma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.giangma.beans.Event;
import ca.sheridancollege.giangma.database.ticketDatabase;

@Controller
public class HomeController {
@GetMapping("/")
public String myRoot() {
	return "root.html";
}

@GetMapping("/add")
public String add(Model model ){
	model.addAttribute("event", new Event());
	return "add.html";
}

@PostMapping("/add")
public String processAdd(@ModelAttribute Event event) {
		System.out.println(event);
		ticketDatabase.ticketList.add(event);
		
	return "redirect:/add";
}

@GetMapping("/view")
public String view(Model model) {
	model.addAttribute("ticketPrint", ticketDatabase.ticketList);
	return "view.html";
}
}
