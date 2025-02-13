package ca.sheridancollege.giangma.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.giangma.beans.Student;
import ca.sheridancollege.giangma.database.StudentDatabase;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {
	
@GetMapping("/")
public String rootPage() {
	return "root.html";
}
@GetMapping("/add")
public String goAdd() {
	return "addStudent.html";
}


@GetMapping("/print")
public String myPrint (@RequestParam String name, @RequestParam int id, 
					@RequestParam(required=false, defaultValue="false") Boolean fulltime
					,@RequestParam String program, @RequestParam int grade) {
	
	
	Student s = new Student(id, name, program, grade, fulltime);
	
	StudentDatabase.studentList.add(s);

	return "addStudent.html";

}

@GetMapping("/view")
public void showBurger(HttpServletResponse response) throws IOException {
	String stu="";
	
	stu+="<ul>";
	for (Student s: StudentDatabase.studentList) {
		stu+= s + "</br>";
	}
	stu+="<ul>";

	
	PrintWriter out = response.getWriter();
	out.println("<html><body>" + "<h1> View Student </h1>" + stu + "</body> </html>");
	
}
}
