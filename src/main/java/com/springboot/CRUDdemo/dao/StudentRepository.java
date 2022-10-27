package com.springboot.CRUDdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.CRUDdemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	// that's it ... no need to write any code LOL!
	
}
