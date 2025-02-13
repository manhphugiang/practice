package ca.sheridancollege.giangma.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class Cupcake {
private String id;
private String cupcakeName;
private double price;
private int quantity;
}
