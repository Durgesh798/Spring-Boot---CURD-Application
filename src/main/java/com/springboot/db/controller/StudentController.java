package com.springboot.db.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.db.model.Student;
import com.springboot.db.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	private StudentService studentservice;

	public StudentController(StudentService studentservice) {
		super();
		this.studentservice = studentservice;
	}
	
    @PostMapping("/")
	public ResponseEntity<Student> saveStudent(@RequestBody Student st){
		return new ResponseEntity<Student>(studentservice.saveStudent(st), HttpStatus.CREATED);
		
	}
    
    @GetMapping("/")
    public List<Student> getAllStudents(){
    	return studentservice.getAllStudents();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long sid){
    	return new ResponseEntity<Student>(studentservice.getStudentById(sid),HttpStatus.OK);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long sid, @RequestBody Student st){
    	return new ResponseEntity<Student>(studentservice.updateStudent(st, sid),HttpStatus.OK);    	    	
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id ){
    	studentservice.deleteStudent(id);
    	
    	return new ResponseEntity<String>("Student deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/getData")
    public String getDataFromExternalApi() {
        return studentservice.getDataFromExternalApi();
    }
}
