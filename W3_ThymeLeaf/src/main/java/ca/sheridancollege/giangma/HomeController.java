package ca.sheridancollege.giangma;

import java.util.ArrayList;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private String message = "GiangMa";

  @GetMapping("/")
  public String myRoot(Model model) {
    model.addAttribute("meow", message);

    ArrayList<String> names = new ArrayList<String>();
    names.add("Joh");
    names.add("Ann");
    names.add("Tod");
    names.add("Ted");
    names.add("Kim");

    model.addAttribute("woof", names);
    return "root.html";
  }
}
