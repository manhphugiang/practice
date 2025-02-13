package ca.sheridancollege.giangma.controller;


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
		public String doRegistration(@RequestParam String username,
				@RequestParam String password) {
		
		secRepo.register(username, password);

		long uid = secRepo.findUserByUserName(username).getUserID();
		// assign as guest
		secRepo.assignRoles(uid, 2);	
		
		return "redirect:/registration";
		}

	
	
}