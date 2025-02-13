package ca.sheridancollege.giangma.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.Student;
import lombok.AllArgsConstructor;
@Repository
@AllArgsConstructor
public class StudentRepository {
	@Autowired

	private NamedParameterJdbcTemplate jdbc;
	public List<Student> getStudent(){
		
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query ="Select * from student";
		
		ArrayList<Student> students = 
				(ArrayList<Student>) jdbc.query(query, parameters,
						new BeanPropertyRowMapper<Student>(Student.class));
		
	
		return students;	
		
	}
	public Student getStudentById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "Select student.id, student.name, student.grade, student.letterGrade "
				+ "from student "
				+ "where id=:id";
		parameters.addValue("id", id);
		
		
		ArrayList<Student> students = 
				(ArrayList<Student>) jdbc.query(query, parameters,
						new BeanPropertyRowMapper<Student>(Student.class));
		
	
		if (students.size() > 0)
			return students.get(0);
		return null;
		
		
	}
	// add the whole table  there is a list 
	
	public void addAllStudents(List<Student> students) {
	    for (Student student : students) {
	    	addCollectorStudent(student);
	    }
	}
	
	
	
	// take the id from the added student 
	
	public Long getIdStudent(String name) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "SELECT id FROM student WHERE name=:name";

	    parameters.addValue("name", name);

	    return jdbc.queryForObject(query, parameters, Long.class);
	}

	
	
	
	
	

	
	
	
	public void editAllStudent(Student student) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "UPDATE student set name=:name WHERE id > 0";
		
		parameters.addValue("name", student.getName());
		
		jdbc.update(query, parameters);
	}
	
	
	// update a single student with it id
	public void updateStudent(int id, String name, double grade) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		String query = "UPDATE student "
				+ "SET name = :name, grade = :grade "
				+ "WHERE id = :id";


		parameters.addValue("id", id);
	    parameters.addValue("name", name);
	    parameters.addValue("grade", grade);
		
		jdbc.update(query, parameters);
		
		
	}
	
	
	// working in class all collection 
	public void addCollectorStudent (Student student) {
		student.calculateLetterGrade();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO student"
				+ "(name, grade, letterGrade) "
				+ "values (:name, :grade, :letter) ";
		
		parameters.addValue("name", student.getName());
		parameters.addValue("grade", student.getGrade());
		parameters.addValue("letter", student.getLetterGrade());
		
		
		jdbc.update(query, parameters);
	}
	
	
	public void deleteAllStudent() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM student";
		
		jdbc.update(query, parameters);
	}
	
	
	
	
	public void editStudent(Student student) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = " UPDATE student "
				+ "SET name =:name"
				+ "where id=:id";
		
		parameters.addValue("name", student.getName());
		parameters.addValue("name", student.getId());

		
		jdbc.update(query, parameters);

	}
	
	public void deleteStudent(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "DELETE FROM student "
				+ "where id=:id";
		
		parameters.addValue("id", id);

		jdbc.update(query, parameters);

		

	}
}
