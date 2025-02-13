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
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.giangma.beans.Drink;
import ca.sheridancollege.giangma.repositories.DrinkRepository;

@SpringBootTest
@AutoConfigureMockMvc
class W42JbdcTemplateApplicationTests {
@Autowired
	private MockMvc mockMvc;
	
@Test
public void testRootPage() throws Exception {
	this.mockMvc.perform(get("/"))
	.andExpect(status().isOk()) // 200 status code
	.andExpect(view().name("root.html"));
	
}

//@Test
//public void testAddDrink() throws Exception {
//	
//	this.mocMvc.perform(get("/add"))
//	.andExpect(model().attributeExists("drink"))
//	.andExpect(status().isOk())
//	.andExpect(view().name("addDrink.html"));
//}
@Test
public void testProcessingAddPAge() throws Exception {
	this.mockMvc.perform(post("/add")
			.flashAttr("drink", new Drink()))
			.andExpect(status().isFound())			// 302 status code
			.andExpect(redirectedUrl("/add"));
			
}
@Test
public void testViewDrink() throws Exception{
	this.mockMvc.perform(get("/view"))
	.andExpect(model().attributeExists("tableDrink"))
	.andExpect(status().isOk())
	.andExpect(view().name("viewDrinks.html"));
}
@Test
public void testDeleteRecord() throws Exception{
	this.mockMvc.perform(get("/delete/{id}", 1))
	.andExpect(status().isFound())
	.andExpect(redirectedUrl("/view"));
}

@Autowired
private DrinkRepository drinkRepo;



@Test
public void checkEmptyDrinkList() {
	ArrayList<Drink> drinks = drinkRepo.getDrinks2();
	if (drinks.size()>0) {
		assert true;
	}
	else {
		assert false;
	}
}

@Test
public void testAddDrink() {
	int startSize = drinkRepo.getDrinks2().size();
	drinkRepo.addDrink(new Drink());
	int endSize = drinkRepo.getDrinks2().size();
	assertTrue(endSize == startSize +1);
}


	@Test
	void contextLoads() {
	}

}
