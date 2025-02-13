package ca.sheridancollege.giangma.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class Accountant {
	private int id;
	private String name;
	private double salary;
	private int yearsExperience;
}
