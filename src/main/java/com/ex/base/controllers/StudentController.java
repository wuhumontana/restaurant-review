package com.ex.base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ex.base.entity.Student;
import com.ex.base.jpa.StudentRepository;
import com.ex.base.services.StudentService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/students")
public class StudentController {
	private List<Student> students = new ArrayList<>();

	private final AtomicLong counter = new AtomicLong();

    @Autowired private StudentRepository repo;
    @Autowired private StudentService studentService;

	public StudentController() {
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<Student>> index() {

		System.out.println("+++++++++++++++++++ StudentController GET ++++++++++++++++");

        try {
            List<Student> students = studentService.getStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 not found
        }
	}
	
    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value="id") Long id) {
        Student s = studentService.findById(id);

        try {
            s = studentService.findById(id);
            return ResponseEntity.ok(s);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 not found
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<Student> addToStudents(
    		@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="address") String address) {
    	Student newStudent = new Student(counter.incrementAndGet(), firstName, lastName, address);
        students.add(newStudent);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(newStudent.getId()).toUri();

        repo.save(newStudent);

        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.location(location)
        		.body(newStudent);
    }
    
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<List<Student>> updateStudent(@PathVariable(value="id") Long id, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="address") String address) {
        students.forEach(student ->  {
            if(student.getId() == id){
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setAddress(address);
            }

        });
        return ResponseEntity.ok(students);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Student>> removeStudent(@PathVariable(value="id") Long id) {
        Student itemToRemove = null;
        for(Student student : students){
            if(student.getId() == id)
                itemToRemove = student;
        }
        students.remove(itemToRemove);
        return ResponseEntity.ok(students);
    }
}