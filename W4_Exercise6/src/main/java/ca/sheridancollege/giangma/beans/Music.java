
package ca.sheridancollege.giangma.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 

@Data
public class Music {
private String title; 
private String genre; 
private String artist;
private double runTimes;


private String[] genres ={ "Indie", "Rap", "Lofi", "Country", "Pop"};

}