package ca.project.giangma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.project.giangma.beans.Item;
import ca.project.giangma.beans.User;
import ca.project.giangma.emails.EmailServiceImpl;
import ca.project.giangma.repository.ItemService;
import ca.project.giangma.repository.UserRepository;
import jakarta.mail.MessagingException;

@Controller
public class homeController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ItemService itemService;

	@Autowired
	private EmailServiceImpl esi;

	@PostMapping(path = "/add")
	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setUserName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}
	

    @GetMapping("/addItem")
    public String showAddItemForm(Model model) {
        // Add any necessary model attributes here
        return "additem.html";
    }
    
    @PostMapping("/addItem")
    public String addNewItem(@ModelAttribute Item newItem) {
        // Process the submitted item and add it to the database
        itemService.addItem(newItem);
        // Redirect to the home page or any other page as needed
        return "redirect:/";
    }
    

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping("/")
	public String root(Model model) {
		
        model.addAttribute("items", itemService.getAllItems());

		return "home.html";
	}

	


    @GetMapping("/email")
    public String EnterEmail(Model model) {
        model.addAttribute("email", "");
        return "email.html";
    }

    
	@PostMapping("/email")
    public String sendEmail(@RequestParam("email") String userEmail, Model model) throws MessagingException {
        
	       List<Item> ỉtemList = itemService.getAllItems();
		esi.sendTechListEmail(ỉtemList, userEmail, "Tech Item Email");
	  return "email.html";
	}
}
