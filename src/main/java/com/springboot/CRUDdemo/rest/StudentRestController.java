package com.springboot.CRUDdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.CRUDdemo.entity.Student;
import com.springboot.CRUDdemo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentRestController {


	private StudentService studentService;

	@Autowired
	public StudentRestController(StudentService theStudentService) {
		studentService = theStudentService;
	}

	@GetMapping("/admin")
	public String admin() {
		return "<h2>Welcome to ADMIN page</h2>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h2>Welcome to USER page</h2>";
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/students")
	public List<Student> findAll() {
		return studentService.findAll();
	}

	@GetMapping("/students/{studentId}")
	public Student getEmployee(@PathVariable int studentId) {
		Student theStudent = studentService.findById(studentId);

		if (theStudent == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}
		return theStudent;
	}

	// add mapping for POST /employees - add new employee
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student theStudent) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		theStudent.setId(0);
		studentService.save(theStudent);

		return theStudent;
	}
	
	// add mapping for PUT /employees - update existing employee
	@PutMapping("/students")
	public Student updateEmployee(@RequestBody Student theStudent) {

		studentService.save(theStudent);

		return theStudent;
	}

	@DeleteMapping("/students/{studentId}")
	public String deleteEmployee(@PathVariable int studentId) {

		Student tempEmployee = studentService.findById(studentId);
		// throw exception if null
		if (tempEmployee == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}
		
		studentService.deleteById(studentId);

		return "Deleted student id - " + studentId;
	}
}
