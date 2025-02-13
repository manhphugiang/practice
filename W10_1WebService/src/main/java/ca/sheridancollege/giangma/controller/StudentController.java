package ca.sheridancollege.giangma.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.giangma.beans.Student;
import ca.sheridancollege.giangma.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
	
	private StudentRepository stuRepo;
	
	//			used to be /students 
	
	@GetMapping(value= {"", "/"})	
	public List<Student> getCollection() {
		return stuRepo.getStudent();
	}
	
	
	@PutMapping(value= {"", "/"}, headers= {"Content-type=application/json"})
	public String putCollection(@RequestBody List<Student>  students) {
		
		 stuRepo.deleteAllStudent();

		
		 stuRepo.addAllStudents(students);

		
		return "Records Edited: " + students.size();

	}
	
	
	@PostMapping(value= {"", "/"})
	public void postCollection(@RequestBody Student student) {
		stuRepo.addCollectorStudent(student);
	}
	
	
	
	
	
	
//	
//	@PostMapping(value={"", "/"}, headers= {"Content-type=application/json"})
//	public String postCollection(@RequestBody  List<Student> students) {
//	    for (Student student : students) {
//	        stuRepo.addStudent(student);
//	    }
//	    Long lastStudentId = stuRepo.getIdStudent(students.get(students.size() - 1).getName());
//
//	    return "Records ID Added: " + lastStudentId;
//}

	@DeleteMapping(value= {"", "/"})	
	public void deleteCollection() {
		stuRepo.deleteAllStudent();
		}

	
	

	
	
	

	
	
	
	
	
	
	

	// 	used to be /students/{id}
	
	@GetMapping(value="/{id}")
	public Student getElement(@PathVariable int id) {
		return stuRepo.getStudentById(id);
	}
	
	
	@PutMapping(value= {"/{id}"}, headers= {"Content-type=application/json"})
	public String putElement(@PathVariable  int id,  @RequestBody Student student ) {

		
		 stuRepo.updateStudent(id, student.getName(), student.getGrade());

		
		return "Records Edited: "; 
	}
	
	
	
	@DeleteMapping(value= {"/{id}"})
	public void deleteElement(@PathVariable  int id) {
		stuRepo.deleteStudent(id);
	}
}

