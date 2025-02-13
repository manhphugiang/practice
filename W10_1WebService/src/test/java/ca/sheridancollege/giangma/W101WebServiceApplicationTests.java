package ca.sheridancollege.giangma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.sheridancollege.giangma.beans.Student;
import ca.sheridancollege.giangma.repositories.StudentRepository;

@SpringBootTest
@AutoConfigureMockMvc
class W101WebServiceApplicationTests {

	@Autowired
	public MockMvc mockMvc;
	
	
//	
//	@RequestMapping("/students")
//	public class StudentController {
//		
//		private StudentRepository stuRepo;
//		
//		//			used to be /students 
//		
//		@GetMapping(value= {"", "/"})	
//		public List<Student> getCollection() {
//			return stuRepo.getStudent();
//		}
//		
//	
//	
	
	
	
	
	
	@Test
	public void testGetCollection() throws Exception {
		String uri = "/students";
		//test a get request using the URI
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		
		// check the status
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		
		//Convert the return type from JSON into an object
		
		String content = mvcResult.getResponse().getContentAsString();
		
		Student[] students = new ObjectMapper().readValue(content, Student[].class);
		
		
		assertTrue(students.length>0);
	}
	// test for a single use case for studenst 1 
	
	@Test 
	public void testStudent1() throws Exception {
		
		String uri ="/students/1";
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		
		//Convert the return type from JSON into an object
		
		String content = mvcResult.getResponse().getContentAsString();
	// change to the single object instead of an array of it	
		Student students = new ObjectMapper().readValue(content, Student.class);
		
		
		assertTrue(students != null);
		
		
	}
	
	
	// test for non exist student 
	
	
//	@Test 
//	public void testStudentNonExist() throws Exception {
//		
//		String uri ="/students/11";
//		
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
//				.accept(MediaType.APPLICATION_JSON_VALUE))
//				.andReturn();
//		
//		
//		
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200,status);
//		
//		//Convert the return type from JSON into an object
//		
//		String content = mvcResult.getResponse().getContentAsString();
//	// change to the single object instead of an array of it	
//		Student students = new ObjectMapper().readValue(content, Student.class);
//		
//		// cai any
//		assertFalse(students != null);
//		
//		
//	}
	
	
	
	
	@Test
	public void testPostCollection() throws Exception {
		String uri = "/students";
// create a student as JSON
		
		Student student = new Student();
		student.setName("Jonathan");
		String studentJson = new ObjectMapper().writeValueAsString(student);
		
		
		// test a post request using the uri 
		
		
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(studentJson))
				.andReturn();
		
		
		// check the status
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		
	
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void contextLoads() {
	}

}
