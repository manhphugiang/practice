package ca.sheridancollege.giangma.beans;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Burger {
private String name;
private boolean vegetarian;
private String toppings; 
private double price;
}
