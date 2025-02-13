package ca.sheridancollege.giangma.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.giangma.product.beans.Product;
import ca.sheridancollege.giangma.repositories.DataRepository;
import ca.sheridancollege.giangma.store.beans.Store;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor

public class storeController {
	private DataRepository dataRepo;

@GetMapping("/")
public String myStore() {
	return "root.html";
}
@GetMapping("/addProduct")
public String addProduct(Model model) {
	
    List<String> storeNames = dataRepo.getAllStoreNames();
    
    model.addAttribute("product", new Product());
    model.addAttribute("storeNames", storeNames);
	return "addProduct.html";
}


@PostMapping("/addProduct")
public String processAddProduct(@ModelAttribute Product product) {
	System.out.println(product);
	dataRepo.addProduct(product);
	return "redirect:/addProduct";
}


@GetMapping("/addStore")
public String addStore(Model model) {
	model.addAttribute("store", new Store());
	return "addStore.html";
}

@PostMapping("/addStore")
public String processAddStore(@ModelAttribute Store store) {
	System.out.println(store);
	dataRepo.addStore(store);
	return "redirect:/addStore";
}

@GetMapping("/view")
public String view(Model model) {
    List<String> storeNames = dataRepo.getAllStoreNames(); 
    model.addAttribute("storeNames", storeNames);
    model.addAttribute("defaultStoreName", "Bookstores"); 
	//model.addAttribute("data", dataRepo.getData());

	return "view.html";
}

@PostMapping("/view")
public String processView(Model model, @RequestParam("selectedStoreName") String selectedStoreName) {
    List<String> storeNames = dataRepo.getAllStoreNames();
    model.addAttribute("storeNames", storeNames);
    model.addAttribute("selectedStoreName", selectedStoreName); 
	model.addAttribute("storeData", dataRepo.getDataForStore(selectedStoreName));
	model.addAttribute("productData", dataRepo.getDataForProduct(selectedStoreName));
    return "view.html";
}











@GetMapping("/edit/{id}")
public String editProduct(Model model, @PathVariable int id) {
    List<String> storeNames = dataRepo.getAllStoreNames();
    model.addAttribute("storeNames", storeNames);
	Product product = dataRepo.getProductById(id);
	
	model.addAttribute("product", product);
	
	return "editProduct.html";
}

@PostMapping("/edit")
public String processEdit(Model model,@ModelAttribute Product product) {
    List<String> storeNames = dataRepo.getAllStoreNames();
    model.addAttribute("storeNames", storeNames);
	dataRepo.editProduct(product);
	return "redirect:/view";
	
}

@GetMapping("/delete/{id}")
	public String deleteProduct(Model model, @PathVariable int id) {
	
		dataRepo.deleteProductById(id);
		return "redirect:/view";
	
	}

}
