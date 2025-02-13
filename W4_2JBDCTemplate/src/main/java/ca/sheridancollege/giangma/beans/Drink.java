package ca.sheridancollege.giangma.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class Drink {
	private int id;
	private String drinkName;
	private String main1;
	private double amount1;
	private String main2;
	private double amount2;
	private String directions;
}
