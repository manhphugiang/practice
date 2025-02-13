package ca.sheridancollege.giangma.product.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	
private int id;
private String productName;
private String storeName;
private String productNumber;
private double price;
}
