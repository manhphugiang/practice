package ca.sheridancollege.giangma.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.giangma.beans.Student;

@Controller
public class StudentController {
	
	final String URL = "http://localhost:8080/students";
	
	@GetMapping("/")
	public String viewsStudent(Model model, RestTemplate restTemplate) {
		
		ResponseEntity<Student[]> responseEntity =
				restTemplate.getForEntity(URL, Student[].class);
		
		Student[] stuList = responseEntity.getBody();
		
		
		model.addAttribute("student", stuList);
		return "viewStudent.html";
	}
	
	
	@GetMapping(value="/getStudent/{id}", produces="application/json")
	@ResponseBody
	public Student getStudent(@PathVariable int id, Model model, RestTemplate restTemplate) {
		
		ResponseEntity<Student> responseEntity = restTemplate.getForEntity(URL+"/"+id, Student.class);
		Student stu = responseEntity.getBody();		
		return stu;
	}
}
