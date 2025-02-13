package ca.sheridancollege.ngquocth.repositories;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.ngquocth.beans.Student;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class StudentRepository {

	
	private NamedParameterJdbcTemplate jdbc;
	
	
	public List<Student> getStudents(){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT* FROM student_table";
		ArrayList<Student> students = 
				(ArrayList<Student>)jdbc.query(query, parameters, 
						new BeanPropertyRowMapper<Student> (Student.class));
		
		return students;
	}
	
	
	public Student getStudentById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		String query = "SELECT* FROM student_table WHERE id=:meow";					//thêm WHERE clause
		parameters.addValue("meow", id);
		ArrayList<Student> students = 
					(ArrayList<Student>)jdbc.query(query, parameters, 		//chọn cái query RowMapper
							new BeanPropertyRowMapper<Student> (Student.class)); 
			
		//only want a single String, this if will check whether drinks is 1 or 0
		//ONLY DO THIS FOR UNIQUE VALUE, IF VALUE IS NOT UNIQUE, just return NULL
		if (students.size()>0)
			return students.get(0);
		else 
			return null;
	}
	
	
	
	public void addStudent (Student student) {
		student.calculateLetterGrade();     	//PHẢI ADD CÁI NÀY ĐỂ TÍNH GRADE TRƯỚC (đáng lẽ sẽ có class StudentService nữa nhưng để clean code)
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		String query = "INSERT INTO student_table (name, grade, letterGrade) VALUES "
				+ "(:na, :gr, :le)"; //put name parameter into query String

		parameters.addValue("na", student.getName());
		parameters.addValue("gr", student.getGrade());
		parameters.addValue("le", student.getLetterGrade());
		jdbc.update(query, parameters);
	}
	
	
	//NOTE: Edit didn't work yet
	public void editStudent (Student student) {
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		String query = "UPDATE student_table SET "						//UPDATE Clause
				+ "name=:na, grade=:gr, letterGrade=:le "
				+ "WHERE id=:meow"; 
				
		parameters.addValue("meow", student.getId());
		parameters.addValue("na", student.getName());
		parameters.addValue("gr", student.getGrade());
		parameters.addValue("le", student.getLetterGrade());
		jdbc.update(query, parameters);
	}
	
	
	
	public void deleteStudentById (int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		String query = "DELETE FROM student_table WHERE id=:meow";
		parameters.addValue("meow", id);
		jdbc.update(query, parameters);
	}

	public void deleteAllStudent () {
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		String query = "DELETE FROM student_table";
		jdbc.update(query, parameters);
	}
	
	
}
