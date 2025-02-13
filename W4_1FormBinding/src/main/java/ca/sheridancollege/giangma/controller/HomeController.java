package ca.sheridancollege.giangma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.beans.Videogame;
import ca.sheridancollege.giangma.database.VideogameDatabase;

@Controller
public class HomeController {

  @GetMapping("/")
  public String myRoot() {
    return "rootPage.html";
  }

  @GetMapping("/add")
  public String addPage(Model model) {
	  model.addAttribute("videogame", new Videogame());
	  //since addGame.html will have form binding 
	  //we need to send a video game object to addGame
    return "addGame.html";
  }
  
  @PostMapping("/add")
  public String processAdd(@ModelAttribute Videogame videogame) {
	  System.out.println(videogame);
//	  model.addAttribute("videogame", new Videogame());
//	  return "addGame.html";
	  VideogameDatabase.videogamelist.add(videogame);
	  
	  return "redirect:/add"; //status 302 
  }
  
  @GetMapping("/view")
  public String viewGames(Model model ) {
	  
	  model.addAttribute("meow", VideogameDatabase.videogamelist);
	  
	  return "viewgames.html";
  }
}
