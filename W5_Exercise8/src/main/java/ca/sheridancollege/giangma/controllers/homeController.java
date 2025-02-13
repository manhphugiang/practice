package ca.sheridancollege.giangma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.giangma.repo.*;
import ca.sheridancollege.giangma.beans.Contacts;


@Controller
public class homeController {
	private contactRepo contactRepo;
	
	@GetMapping("/")
	public String addDrink(Model model) {
		model.addAttribute("drink", new Contacts());
		return "addContact.html";
	}
	@PostMapping("/saveContact")
	public String processAdd(@ModelAttribute Contacts contact) {
		System.out.println(contact);
		contactRepo.addContact(contact);
		return "redirect:/add";
	}

	@GetMapping("/view")
	public String viewDrink(Model model) {
	  model.addAttribute("tableDrink", contactRepo.getContact());
	  return "viewContacts.html";
	}

	@GetMapping("/edit/{id}")
	public String editDrink(Model model, @PathVariable int id) {
		
		Contacts contact = contactRepo.getContactById(id);
		
		model.addAttribute("Contacts", contact);
		
		return "editContact.html";
	}

	@PostMapping("/edit")
	public String processEdit(Model model,@ModelAttribute Contacts contact) {
		contactRepo.editContact(contact);
		return "redirect:/view";
		
	}


		@GetMapping("/delete/{id}")
		public String deleteDrink(Model model, @PathVariable int id) {
			contactRepo.deleteContactById(id);
			return "redirect:/view";
		
		}



	}