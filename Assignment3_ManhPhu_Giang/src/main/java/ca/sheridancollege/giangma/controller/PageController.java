package ca.sheridancollege.giangma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.giangma.beans.Ticket;
import ca.sheridancollege.giangma.repositories.TicketRepository;

@Controller
public class PageController {
	@Autowired
	private TicketRepository ticketRepo;
	
	
@GetMapping("/")
public String rootPage() {
	return "root.html";
}




@GetMapping("/viewGuest")
public String viewTicket(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String currentUsername = userDetails.getUsername();

    List<Ticket> userTickets = ticketRepo.viewGuestTicket(currentUsername);
    model.addAttribute("ticketData", userTickets);

    // Calculate subtotal, taxes, and total for the guest
    double subtotal = 0;
    double taxes = 0;
    double total =0;
    for (Ticket ticket : userTickets) {
        subtotal += ticketRepo.getSubtotal(ticket.getName());
        taxes += ticketRepo.getTaxes(ticket.getName());
        total += ticketRepo.getTotal(ticket.getName());

    }

    
    model.addAttribute("subtotal", subtotal);
    model.addAttribute("taxes", taxes);
    model.addAttribute("total", total);

    return "viewGuestTicket.html";
}



@GetMapping("/viewVender")
public String viewVenderTicket(Model model) {

	  model.addAttribute("ticketData", ticketRepo.viewAllTicket());

	
  return "viewTicket.html";
}


@GetMapping("/addTicket")
public String addTicket(Model model) {
	 model.addAttribute("ticket", new Ticket());
     model.addAttribute("guest", ticketRepo.getAllGuestUsernames());

     
	return "addTicket.html";
}



@PostMapping("/add")
public String addGuestTicket(@ModelAttribute Ticket ticket, Model model) {
	
	

    switch (ticket.getTicketType()) {
        case "General Admission":
            ticket.setPrice(50.99);
            ticket.setTicketType("General Admission");

            break;
        case "VIP":
            ticket.setPrice(100.99);
            ticket.setTicketType("VIP");

            break;
        case "Student Discount":
            ticket.setPrice(30.99);
            ticket.setTicketType("Student Discount");

            break;
    }
 
    ticket.setName(ticket.getSelectedGuest());
    model.addAttribute("ticket", ticket);

   ticketRepo.addGuestTicket(ticket);

    return "redirect:/addTicket";

}



@GetMapping("/delete/{id}")
public String deleteTicketById(Model model, @PathVariable int id) {
	ticketRepo.deleteTicketById(id);
	return "redirect:/viewVender";

}

@GetMapping("/edit/{id}")
public String editTicket(Model model, @PathVariable int id) {
	
	Ticket ticket = ticketRepo.getTicketById(id);
	
	model.addAttribute("ticket", ticket);
	
	return "editTicket.html";
}

@PostMapping("/edit")
public String processEdit(Model model,@ModelAttribute Ticket ticket) {
	
    switch (ticket.getTicketType()) {
    case "General Admission":
        ticket.setPrice(50.99);
        ticket.setTicketType("General Admission");

        break;
    case "VIP":
        ticket.setPrice(10.99);
        ticket.setTicketType("VIP");

        break;
    case "Student Discount":
        ticket.setPrice(30.99);
        ticket.setTicketType("Student Discount");

        break;
}
	ticketRepo.editTicket(ticket);
	return "redirect:/viewVender";
	
}


}

