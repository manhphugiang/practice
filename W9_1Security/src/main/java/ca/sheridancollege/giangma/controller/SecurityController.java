package ca.sheridancollege.giangma.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.giangma.repositories.SecurityRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SecurityController {
	
	
	private SecurityRepository secRepo;
	
	@GetMapping("/login")
	public String loginRoot() {
		return "login.html";
	}
	
	@GetMapping ("/access-denied")
	public String ad() {
		return "access-denied.html";
	}
	
	@GetMapping("/registration")
	public String registrationController() {
		return "registration.html";
	}
	
	@PostMapping("/registration")
	public String registrationAdd(@RequestParam String username, @RequestParam String password) {
		
		secRepo.register(username, password);
		long uid = secRepo.findUserByUserName(username).getUserID();
		secRepo.assignRoles(uid, 1); //Admin
		secRepo.assignRoles(uid, 2); //User
		secRepo.assignRoles(uid, 3); // Pickle
		secRepo.assignRoles(uid, 4); // Cream

		return "redirect:/registration";
	}
}
