package ca.sheridancollege.giangma.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class Ticket {
	
    private String selectedGuest;

	
	private int id;
	private String name;
	private double price;
	
	private double creditCardNumber;
	private String ticketType;
	private String email;
	private int cvv;
}
