package ca.sheridancollege.giangma.controllers;

import ca.sheridancollege.giangma.beans.Music;
import ca.sheridancollege.giangma.database.musicDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class musicController {

  @GetMapping("/")
  public String myRoot() {
    // Get the remote (client) IP address
    return "rootMusic.html";
  }

  // Your other controller methods...

  @GetMapping("/add")
  public String addMusic(Model model) {
    model.addAttribute("music", new Music());
    return "addMusic.html";
  }

  @PostMapping("/add")
  public String processAdd(@ModelAttribute Music music) {
    System.out.println(music);
    musicDatabase.musicList.add(music);
    return "redirect:/add";
  }

  @GetMapping("/view")
  public String viewMusic(Model model) {
    model.addAttribute("goodMusic", musicDatabase.musicList);
    return "viewMusic.html";
  }
}
