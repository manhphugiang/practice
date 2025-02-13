package ca.sheridancollege.giangma.beans;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
	@Getter
	@Setter
	@ToString
	@EqualsAndHashCode
@Data

public class Student {
	private int id;
	private String name;
	private String program;
	private double grade;
	private boolean fulltime;
	
	
}
