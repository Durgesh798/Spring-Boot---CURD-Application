package com.springboot.db.service;

import java.util.List;

import com.springboot.db.model.Student;

public interface StudentService {

	Student saveStudent(Student st);
	List<Student> getAllStudents();
	Student getStudentById(long id);
	Student updateStudent(Student st, long id);
	void deleteStudent(long id);
	String getDataFromExternalApi();
}
