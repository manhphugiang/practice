package ca.sheridancollege.ngquocth.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

	private int id;
	private String name;
	private double grade;
	private String letterGrade;
	
	
	
	
	
	
	public void calculateLetterGrade() {
		if(grade >= 80) 
			this.letterGrade= "A";
		else if (grade >= 70)this.letterGrade="B";
		else if (grade >= 60)this.letterGrade="C";
		else if (grade >= 70)this.letterGrade="D";
		else this.letterGrade="F";
			
	}






	public Student(String name, double grade) {
		this.name = name;
		this.grade = grade;
		this.calculateLetterGrade();
	}





	public void setGrade(double grade) {
		this.grade = grade;
		this.calculateLetterGrade();
	} 
	
	
	
	
	
	
	
}
