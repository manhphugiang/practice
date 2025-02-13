package ca.sheridancollege.giangma;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.sheridancollege.giangma.beans.Contacts;
import ca.sheridancollege.giangma.beans.User;
import ca.sheridancollege.giangma.repo.ContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
class Exercise8ApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test 
	public void testLogin() throws Exception{
		this.mockMvc.perform(get("/delete/{id}", 1)) 
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("http://localhost/login"));

		

	}

	
	
	
	@Test
	public void testAddContact() throws Exception {
	    mockMvc.perform(get("/"))
	            .andExpect(status().is3xxRedirection()) 
	            .andExpect(redirectedUrl("/saveContact"));
	}


@Test
public void testProcessAdd() throws Exception{
	mockMvc.perform(get("/saveContact"))
	.andExpect(status().isOk())
	.andExpect(view().name("addContact.html"));
}
@Test
public void testViewContact() throws Exception{
	mockMvc.perform(get("/view"))
	.andExpect(status().isOk())
	.andExpect(view().name("viewContacts.html"));
}

@Test
public void testEditContact2() throws Exception {
    int contactId = 2; 
    mockMvc.perform(get("/edit/{id}", contactId))
            .andExpect(status().isOk()) 
            .andExpect(view().name("editContact.html"));
}@Test
public void testEditContact3() throws Exception {
    int contactId = 3; 
    mockMvc.perform(get("/edit/{id}", contactId))
            .andExpect(status().isOk()) 
            .andExpect(view().name("editContact.html"));
}
@Test 
public void testDeleteDrink() throws Exception{
	this.mockMvc.perform(get("/delete/{id}", 1)) 
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/view"));

	

}



//TEST REPOSITORY

	@Autowired
	public ContactRepository ContactRepo;
	@Test
	public void checkEmptyContactList() {
		ArrayList<Contacts> contacts = ContactRepo.getContact();
		if(contacts.size()>0)
			assert true;
		else
			assert false;
	}
	@Test 

	public void testAddContact1() {
		int startSize = ContactRepo.getContact().size();
		ContactRepo.addContact(new Contacts());
		int endSize = ContactRepo.getContact().size();
		assertTrue(endSize == startSize + 1);
	}
	@Test 
	public void testEditContact() {
		int startSize = ContactRepo.getContact().size();
		ContactRepo.editContact(new Contacts());
		int endSize = ContactRepo.getContact().size();
		assertTrue(endSize == startSize);
	}
	@Test 
	public void testDeleteById() {
		int startSize = ContactRepo.getContact().size();
		ContactRepo.deleteContactById(2);
		int endSize = ContactRepo.getContact().size();
		assertTrue(endSize == startSize -1);
	}





// test security


@Test
public void testGetCollection() throws Exception{
	String uri = "/login";
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
	User[] users = new ObjectMapper().readValue(content, User[].class); //then convert that string to an array
	assertTrue(users.length>0); //if JSON is not empty then get a collection of students


}

}