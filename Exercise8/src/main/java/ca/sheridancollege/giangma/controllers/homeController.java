package ca.sheridancollege.giangma.controllers;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.giangma.repo.*;
import lombok.AllArgsConstructor;
import ca.sheridancollege.giangma.beans.Contacts;


@Controller
@AllArgsConstructor
public class homeController {
	
	private ContactRepository contactRepo;
	
	
	@GetMapping("/")
	public String addContact() {
		return "redirect:/saveContact";
	}
	
	
	@GetMapping("/saveContact")
	public String addContact(Model model) {
		model.addAttribute("contact", new Contacts());
		return "addContact.html";
	}
	@PostMapping("/saveContact")
	public String processAdd(@ModelAttribute Contacts contact) {
		System.out.println(contact);
		contactRepo.addContact(contact);
		return "redirect:/";
	}

//	@GetMapping("/view")
//	public String viewContact(Model model) {
//	  model.addAttribute("contact", contactRepo.getContact());
//	  return "viewContacts.html";
//	}

	
	@GetMapping("/view")
	public String viewContact(Model model, Authentication authentication) {
	    String role = getRole(authentication); // Get the role of the authenticated user
	    ArrayList<Contacts> contacts = new ArrayList<>();

	    if ("guest".equals(role)) {
	        contacts.addAll(contactRepo.getGuestContacts());
	    }
	    if ("member".equals(role)) {
	        contacts.addAll(contactRepo.getMembersContacts());
	    }
	    if ("admin".equals(role)) {
	        contacts.addAll(contactRepo.getAdminContacts());
	    }

	    model.addAttribute("contacts", contacts); // Use 'contacts' instead of 'contact' for the model attribute
	    return "viewContacts.html";
	}

	// Helper method to get the role of the authenticated user
	private String getRole(Authentication authentication) {
	    for (GrantedAuthority authority : authentication.getAuthorities()) {
	        if ("ROLE_GUEST".equals(authority.getAuthority())) {
	            return "guest";
	        } else if ("ROLE_MEMBER".equals(authority.getAuthority())) {
	            return "member";
	        } else if ("ROLE_ADMIN".equals(authority.getAuthority())) {
	            return "admin";
	        }
	    }
	    return null;
	}
	
	
	
	
	
	
	
	

	
	@GetMapping("/edit/{id}")
	public String editContact(Model model, @PathVariable int id) {
	    Contacts contacts = contactRepo.getContactById(id);
	    model.addAttribute("contact", contacts); // Add 'contact' as a model attribute
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

