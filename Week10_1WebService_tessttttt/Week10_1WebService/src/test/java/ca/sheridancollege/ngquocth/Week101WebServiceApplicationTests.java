package ca.sheridancollege.ngquocth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.sheridancollege.ngquocth.beans.Student;

@SpringBootTest
@AutoConfigureMockMvc
class Week101WebServiceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	//HOW WE TEST WEB SERVICES

	
	//Cái này test GetMapping List of Student
	@Test
	public void testGetCollection() throws Exception{
		String uri = "/students";
		//Test a get request using the URI
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		//check the status
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status); //change status to 200
		
		//Convert the return type from JSON into an object
		//convert list of students to a string
		String content = mvcResult.getResponse().getContentAsString(); //convert JSON to a string
		Student[] students = new ObjectMapper().readValue(content, Student[].class); //then convert that string to an array
		assertTrue(students.length>0); //if JSON is not empty then get a collection of students

	
	}
	
	
	//Cái này test GetMapping for single student (element)
	//test id lấy student "1"
	@Test
	public void testGetElement() throws Exception{
		String uri = "/students/1";
		//Test a get request using the URI
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		//check the status
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status); //change status to 200
		
		//Convert the return type from JSON into an object
		//convert list of students
		String content = mvcResult.getResponse().getContentAsString(); //convert JSON to an array
		Student student = new ObjectMapper().readValue(content, Student.class);
		assertTrue(student != null ); //if object is exist

	
	}
	
	
	@Test
	public void testNonExistElement() throws Exception{
		String uri = "/students/11";
		//Test a get request using the URI
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		//check the status
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status); //change status to 200
		
		//Convert the return type from JSON into an object
		//convert list of students
		String content = mvcResult.getResponse().getContentAsString(); //convert JSON to an array
		
		assertTrue(content.equals("")); //if object is exist

	
	}
	
	
	
	@Test
	public void testPostCollection() throws Exception{
		String uri = "/students";
		//Create a Student as JSON
		Student student = new Student();
		student.setName("Thai");
		String studentJson = new ObjectMapper().writeValueAsString(student);

		
		//Test post request using the URI
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(studentJson))
				.andReturn();
		
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status); 
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void contextLoads() {
	}
	
}
