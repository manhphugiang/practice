package ca.sheridancollege.giangma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.giangma.beans.Accountant;
import ca.sheridancollege.giangma.emails.EmailServiceImpl;
import ca.sheridancollege.giangma.repository.AccountantRepository;
import jakarta.mail.MessagingException;



@Controller
public class homeController {
	
	@Autowired
	private EmailServiceImpl esi;
	@Autowired
	private AccountantRepository accRepo;
	
	
	@GetMapping("/")
	public String rootToot() {
	
	return "email.html";
	
	}
	
	
	@GetMapping("/add")
	public String addAccountant() {
		return "addAccountant.html";
	}
	
	
	@PostMapping("/add")
	public String addedAccountant( @ModelAttribute Accountant accountant) {
		

	    accRepo.addAccountant(accountant);
	
	    return "redirect:/add";

	}
	
	
	@GetMapping("/view")
	public String viewAccountant(Model model) {
		  model.addAttribute("accountants", accRepo.getAccountant());

		return "viewAccountant.html";
	}
	

	@GetMapping("/delete/{id}")
	public String deleteDrink(Model model, @PathVariable int id) {
		accRepo.deleteAccountantById(id);
		return "redirect:/view";
	}
	
	
    @GetMapping("/email")
    public String EnterEmail(Model model) {
        model.addAttribute("email", "");
        return "email.html";
    }
	
	
	@PostMapping("/email")
    public String sendEmail(@RequestParam("email") String userEmail, Model model) {
        
	       try {
	            List<Accountant> accountantList = accRepo.getAccountant();
	            esi.sendAccountantListEmail(accountantList, userEmail, "Drink List Email");
	        } catch (MessagingException e) {
	            model.addAttribute("error", "Error sending email: " + e.getMessage());
	        }
	  return "email.html";
	}
}
	
	
	

     

