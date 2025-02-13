package ca.sheridancollege.giangma.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import ca.sheridancollege.giangma.repo.SecurityRepository;
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
				@RequestParam String password, 
				 @RequestParam List<String> roles) {
		
		secRepo.register(username, password);

		long uid = secRepo.findUserByUserName(username).getUserID();

	    for (String roleId : roles) {
	        secRepo.assignRoles(uid, Long.parseLong(roleId));
	    }		
		
		return "redirect:/registration";
		}
	
}
