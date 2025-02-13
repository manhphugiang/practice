package ca.sheridancollege.giangma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.giangma.beans.Cupcake;
import ca.sheridancollege.giangma.repositories.CupcakeRepository;

@Controller
public class CupcakeController {
	@Autowired
	private CupcakeRepository cupcakeRepo;

@GetMapping("/")
public String rootCupcake() {
	return "rootCupcake.html";
}

@GetMapping("/addCupcake")
public String addCupcake(Model model) {
	model.addAttribute("cupcake", new Cupcake());
	return "addCupcake.html";
}

@PostMapping("/addCupcake")
public String processAddCupcake(@ModelAttribute Cupcake cupcake) {
	cupcakeRepo.addCupcake(cupcake);
	return "redirect:/addCupcake";
}



@GetMapping("/view")
public String viewCupcake(Model model) {
	  model.addAttribute("cupcake", cupcakeRepo.getCupcake());
	  return "viewCupcake.html";
	
	
}
@GetMapping("/delete/{id}")
public String deleteDrink(Model model, @PathVariable int id) {
	cupcakeRepo.deleteCupcakeById(id);
	return "redirect:/view";
}
@GetMapping("/purchase/{id}")
public String purchaseCupcake(Model model, @PathVariable int id) {
	cupcakeRepo.purchaseCupcakeById(id);
	return "redirect:/view";
}


}




