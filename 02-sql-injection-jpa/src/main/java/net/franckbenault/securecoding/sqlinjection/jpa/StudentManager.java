package net.franckbenault.jpa.hibernate;

import java.util.List;

public interface StudentManager {

	Student createStudent(Student student);
	
	void removeStudent(Student student);
	
	List<Student> findAllStudents();
	
}
