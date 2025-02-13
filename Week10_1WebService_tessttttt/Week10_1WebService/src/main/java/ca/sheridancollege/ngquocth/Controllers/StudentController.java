package ca.sheridancollege.ngquocth.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.ngquocth.beans.Student;
import ca.sheridancollege.ngquocth.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@RestController                    //KHÁC CHỖ NÀY
@AllArgsConstructor
@RequestMapping("/students")    //cái này sẽ thay thế prefix của tất cả các mapping có trong class này
public class StudentController {
	
	private StudentRepository stuRepo;
	
	//@RestCOntroller convert methods to JSON
	
	
//GET : for collection and element	
	//show tất cả student đang có trong database
	//nghĩa là          /students or /students/    chứ k phải "/" của homepage, gõ "localhost:8080/" trong url sẽ k ra vì k có tạo
	@GetMapping (value={"", "/"})
	public List<Student> getCollection() {
		return stuRepo.getStudents();
	}


	//search url id nào ra student tương ứng
	//                  /students/{id}
	@GetMapping (value={"/{id}"})
	public Student getElement(@PathVariable int id) {
		return stuRepo.getStudentById(id);
	}
	

	

	
//POST : for collection only
	//add từng student
	//single student
	@PostMapping (value={"", "/"}, headers= {"Content-type=application/json"}) 
	public void postCollection(@RequestBody Student student) {
		stuRepo.addStudent(student);
		//no need to include id
	}


	
	
	
	
	
	
	
//PUT : for collection and element	
	
	//Put là update/replace vào database
	//list of student
	@PutMapping (value={"", "/"}, headers= {"Content-type=application/json"})  //Put là update
	public void putCollection(@RequestBody List<Student> students) {
		for (Student s : students) {
			stuRepo.editStudent(s);
		}
	//cái này return a collection nên khi dùng PUT, phải có [], ở bên trong là {}--> [ {} ]	
	//edit multiple record at the same time, remember to include id
	
	}
	
	
	@PutMapping (value={"/{id}"})
	public void putElement(@PathVariable int id) {
		Student student = stuRepo.getStudentById(id);
		stuRepo.editStudent(student);
	}
	
	
	
//DELETE : for collection and element		
	@DeleteMapping (value={"", "/"}, headers= {"Content-type=application/json"})
	public void deleteCollection() {
		stuRepo.deleteAllStudent();
		}
	
	
	
	@DeleteMapping (value={"/{id}"})
	public void deleteElement(@PathVariable int id) {
		stuRepo.deleteStudentById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
