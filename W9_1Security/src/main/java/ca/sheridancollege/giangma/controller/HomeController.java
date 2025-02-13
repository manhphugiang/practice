package ca.sheridancollege.giangma.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
	@GetMapping("/")
	public String root() {
		return "root.html";
	}
	
	@GetMapping("/page1")
	public String page1(Authentication auth) {
		
		//username of the person logged in 
		String username = auth.getName();		
		
		
		
		List<String> roles = new ArrayList<String>();
		
		
		
		List<GrantedAuthority> grantList 
			= (List<GrantedAuthority>) auth.getAuthorities();
		
		
		
		for(GrantedAuthority g: grantList) {
			roles.add(g.getAuthority());
		}
		
		return "page1.html";
	}

	

	@GetMapping("/page2")
	public String page2() {
		return "page2.html";
	}
	
}
