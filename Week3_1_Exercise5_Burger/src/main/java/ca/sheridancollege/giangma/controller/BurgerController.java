package ca.sheridancollege.giangma.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.giangma.beans.Burger;
import ca.sheridancollege.giangma.database.BurgerDatabase;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class BurgerController {
	@GetMapping("/")
	public String rootPage() {
		return "root.html";

}

	@GetMapping("/add")
	public String goAdd() {
		return "addBurger.html";
	}

	
	@GetMapping("/print")
	public String myPrint (@RequestParam String name, 
						@RequestParam(required=false, defaultValue="false") Boolean vege
						,@RequestParam String toppings, @RequestParam int price) {
		
		
		Burger b = new Burger(name, vege, toppings, price);
		
		BurgerDatabase.burgerList.add(b);

		return "addBurger.html";
	}
	
	@GetMapping("/view")
	public void showBurger(HttpServletResponse response) throws IOException {
		String bur="";
		
		bur+="<ul>";
		for (Burger b: BurgerDatabase.burgerList) {
			bur+= b + "</br>";
		}
		bur+="<ul>";

		
		PrintWriter out = response.getWriter();
		out.println("<html><body>" + "<h1> View Burger </h1>" + bur + "</body> </html>");
	
}
}
