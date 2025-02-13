package ca.sheridancollege.giangma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class rootController {

	
	@GetMapping("/")
	public String rootToot(HttpSession session, @RequestParam(required=false) String name) {
		
		
		if(name != null && !name.equals("")) {
			session.setAttribute("name", name); //store name in the session
		}
		
		if(session.isNew()) {
			session.setAttribute("meow", "Welcome to Sessions");
			session.setAttribute("isnew", true);
			session.setAttribute("sessionID", session.getId());
			session.setMaxInactiveInterval(5);	//session time out after 5 seconds(how long client response to the server)
		}else {									//lúc này khi nhập tên vào trên 5s, nó sẽ k hiển thị tên ra mà reload lại chỗ nhập, phải nhập dưới 5s thì mới hiện ra
			session.setAttribute("meow", "Welcome back");
			session.setAttribute("isnew", false);
			session.setAttribute("sessionID", session.getId());
		}
		return "root.html";
	}
	
	
	@GetMapping("/add")
	public String rootToot2() {
		return "add.html";
	}
	
	
	//End Session (3 ways)
	//1. logout -> invalidate the session
	//2. brower/server closed (shutdown)
	//3. session times out
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //tell the server to forget the session
		return "redirect:/"; //when it's ended, I redirect it to /p1, then it run above /p1 to create another new session
								//that's why it's displayed welcome to sessions again
	}
	
	
	
	
	
	
	
	
	
}
