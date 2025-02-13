package ca.sheridancollege.giangma;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @GetMapping("/")
  public String myRoot() {
    return "login.html";
  }

  @GetMapping("/check")
  public String checkPassword(
    @RequestParam String username,
    @RequestParam String password
  ) {
    if (username.equals("giangma") && password.equals("123")) {
      return "welcome.html";
    } else {
      return "nope.html";
    }
  }

  @GetMapping("/print")
  public String myPrint(
    @RequestParam String studentname,
    @RequestParam String id,
    @RequestParam(required = false) String one,
    @RequestParam(required = false) String two,
    @RequestParam(required = false) String three,
    @RequestParam(required = false) String four,
    @RequestParam(required = false, defaultValue = "false") Boolean coop,
    @RequestParam(required = false, defaultValue = "false") Boolean fulltime,
    @RequestParam(required = false, defaultValue = "false") Boolean paid
  ) {
    System.out.println(
      "Student Name: " +
      studentname +
      "  " +
      "  Student ID: " +
      id +
      "  " +
      "Co-Op: " +
      coop +
      "  " +
      "Full Time Student: " +
      fulltime +
      "  " +
      "Paid Tuition: " +
      paid +
      "  " +
      "Course Enrolled: " +
      one +
      "  " +
      two +
      "  " +
      three +
      "  " +
      four
    );

    {
      return "welcome.html";
    }
  }
}
