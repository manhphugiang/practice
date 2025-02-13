package ca.sheridancollege.giangma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.giangma.beans.Drink;
import ca.sheridancollege.giangma.emails.EmailServiceImpl;
import ca.sheridancollege.giangma.repositories.*;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DrinkController {
	@Autowired
	private EmailServiceImpl esi;
	
private DrinkRepository drinkRepo;
//@GetMapping("/")
//public String addDrinkPage() {
//	
////	Drink d = new Drink("Meow meow", "Cat", 20, "Catnip", 10, "Throw and Run");
////	drinkRepo.addDrink(d);
//	for(Drink dr : drinkRepo.getDrinks2()) {
//		System.out.println(dr);
//	}
//	return "root.html";
//}
@GetMapping("/add")
public String addDrink(Model model) {
	model.addAttribute("drink", new Drink());
	return "addDrink.html";
}
@PostMapping("/add")
public String processAdd(@ModelAttribute Drink drink) {
	System.out.println(drink);
	drinkRepo.addDrink(drink);
	return "redirect:/add";
}

@GetMapping("/view")
public String viewDrink(Model model) {
	
	
    try {
        List<Drink> drinkList = drinkRepo.getDrinks2();
        esi.sendDrinkListEmail(drinkList, "gmanhphuu@gmail.com", "Drink List Email");
    } catch (MessagingException e) {
        model.addAttribute("error", "Error sending email: " + e.getMessage());
    }
	
	
	
  model.addAttribute("tableDrink", drinkRepo.getDrinks2());
  
  return "viewDrinks.html";
}

@GetMapping("/edit/{id}")
public String editDrink(Model model, @PathVariable int id) {
	
	Drink drink = drinkRepo.getDrinkById(id);
	
	model.addAttribute("drink", drink);
	
	return "editDrink.html";
}

@PostMapping("/edit")
public String processEdit(Model model,@ModelAttribute Drink drink) {
	
	
	
	drinkRepo.editDrink(drink);
	return "redirect:/view";
	
}


	@GetMapping("/delete/{id}")
	public String deleteDrink(Model model, @PathVariable int id) {
		drinkRepo.deleteDrinkById(id);
		return "redirect:/view";
	
	}



}
