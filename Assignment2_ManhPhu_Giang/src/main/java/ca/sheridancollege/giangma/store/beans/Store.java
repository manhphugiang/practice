package ca.sheridancollege.giangma.store.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class Store {

private int id;
private String storeName;
private String location;
private String numberEmployee;
private String storeManager;
}
