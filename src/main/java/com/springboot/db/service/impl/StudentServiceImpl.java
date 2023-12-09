package com.springboot.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.db.exception.ResourceNotFoundException;
import com.springboot.db.model.Student;
import com.springboot.db.repository.StudentRepository;
import com.springboot.db.service.StudentService;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentServiceImpl implements StudentService{

private StudentRepository repo;

	private RestTemplate restTemplate;

	@Autowired
	public void MyService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public StudentServiceImpl(StudentRepository repo, RestTemplate restTemplate) {
		super();
		this.repo = repo;
		this.restTemplate = restTemplate;
	}

	public String getDataFromExternalApi() {
		String apiUrl = "https://restcountries.com/v3.1/name/united";
		return restTemplate.getForObject(apiUrl, String.class);
	}

	public Student saveStudent(Student st) {
		return repo.save(st);
	}

	@Override
	public List<Student> getAllStudents() {
		return repo.findAll();
	}

	@Override
	public Student getStudentById(long id) {
//		Optional<Student> st = repo.findById(id);
//		if(st.isPresent()) {
//			return st.get();
//		}
//		else
//		{
//			throw new ResourceNotFoundException("Student", "Id", id);
//		}
		
		return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student", "Id", id));
			
	}

	@Override
	public Student updateStudent(Student st, long id) {
		Student stu=repo.findById(id).orElseThrow(()->
				new ResourceNotFoundException("Student", "Id", id));
		stu.setName(st.getName());
		stu.setEmail(st.getEmail());
		
		repo.save(stu);
		return stu;
	}

	@Override
	public void deleteStudent(long id) {
		repo.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Student", "Id", id));
		repo.deleteById(id);
	}
	
}
