package com.springboot.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.db.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
