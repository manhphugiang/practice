package ca.sheridancollege.giangma;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
@AutoConfigureMockMvc

class W91SecurityApplicationTests {
@Autowired
public MockMvc mockMvc;

//@GetMapping("/")
//public String root() {
//	return "root.html";
//}

@Test
public void testRoot() throws Exception{
			this.mockMvc.perform(get("/"))
						.andExpect(status().isOk())
						.andExpect(view().name("root.html"));
}


//
//@GetMapping("/page1")
//public String page1(Authentication auth) {
//	
//	//username of the person logged in 
//	String username = auth.getName();		
//	
//	
//	
//	List<String> roles = new ArrayList<String>();
//	
//	
//	
//	List<GrantedAuthority> grantList 
//		= (List<GrantedAuthority>) auth.getAuthorities();
//	
//	
//	
//	for(GrantedAuthority g: grantList) {
//		roles.add(g.getAuthority());
//	}
//	
//	return "page1.html";
//}


@Test
@WithMockUser(roles="USER")
public void testPage1AsUser() throws Exception{
			this.mockMvc.perform(get("/page1"))
						.andExpect(status().isOk())
						.andExpect(view().name("page1.html"));
}
@Test
@WithMockUser(roles="CREAM")
// it can also check the username WithMockUser(username="Jon") 
public void testPage1AsCream() throws Exception{
			this.mockMvc.perform(get("/page1"))
						.andExpect(status().isFound())
						.andExpect(redirectedUrl("/access-denied"));
}

@Test
public void testUserPageNotLoggedIn() throws Exception {
	this.mockMvc.perform(get("/page1"))
	.andExpect(status().isFound())
	.andExpect(redirectedUrl("**/login.html"));
}





	@Test
	void contextLoads() {
	}

}
