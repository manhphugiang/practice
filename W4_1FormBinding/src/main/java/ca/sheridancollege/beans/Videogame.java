package ca.sheridancollege.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 

@Data
public class Videogame {
private String title; 
private double price; 
private String console;
private int numPlayers;


private String[] consoles ={ "PS5", "XBox", "Nintendo", "PC"};

}
