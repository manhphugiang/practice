package ca.sheridancollege.giangma.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.giangma.beans.Accountant;
import ca.sheridancollege.giangma.repository.AccountantRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/accountants")
public class accountantRestController {

	private AccountantRepository accRepo;

	@PostMapping(value= {"", "/"})
	public void postAccountant(@RequestBody Accountant accountant) {
		accRepo.addAccountant(accountant);
	}
	
	
	@GetMapping(value= {"", "/"})	
	public List<Accountant> getCollection() {
		return accRepo.getAccountant();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Accountant> getAccountantById(@PathVariable int id) {
	    Accountant accountant = accRepo.getAccountantById(id);

	    if (accountant != null) {
	        return new ResponseEntity<>(accountant, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> calculateProgressiveTax(@PathVariable int id, @RequestBody Accountant accountant) {

		Accountant existAccountant = accRepo.getAccountantById(id);

	    if (existAccountant == null) {
	        return new ResponseEntity<>("Accountant not found", HttpStatus.NOT_FOUND);
	    }

	    double salary = existAccountant.getSalary();
	    double progressiveTaxRate = calculateProgressiveTaxRate(salary);

	    return new ResponseEntity<>(progressiveTaxRate, HttpStatus.OK);
	}

	private double calculateProgressiveTaxRate(double salary) {
	    if (salary < 40000) {
	        return 0.20 * salary;
	    } else if (salary <= 100000) {
	        return 8000 + 0.30 * (salary - 40000);
	    } else {
	        return 26000 + 0.40 * (salary - 100000);
	    }
	}


	
}
