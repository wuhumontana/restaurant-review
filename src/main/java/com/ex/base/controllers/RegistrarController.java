package com.ex.base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ex.base.jpa.RegistrarRepository;
import com.ex.base.services.RegistrarService;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class RegistrarController {

    @Autowired private RegistrarService registrarService;
	@Autowired private RegistrarRepository repo;

	private HashMap<Integer, ArrayList<Integer>> registrars = new HashMap<Integer, ArrayList<Integer>>();

	public RegistrarController() {
        int exampleCourseNum = 101;
        addEntry(exampleCourseNum, 1);
        addEntry(exampleCourseNum, 2);
    }

    public ArrayList<Integer> addEntry(Integer courseNumber, Integer studentId) {
        ArrayList<Integer> students = registrars.get(courseNumber);

        if (students == null || students.isEmpty()) {
            students = new ArrayList<>();
        }
        students.add(studentId);
        registrars.put(courseNumber, students);
        return students;
    }

	@GetMapping(value = "/registrars/")
	public ResponseEntity index() {
		System.out.println("+++++++++++++++++++ RegistrarController: GET ++++++++++++++++");
		return ResponseEntity.ok(registrars);
	}

	@GetMapping(value = "/registrars/{courseId}")
	public ResponseEntity studentsInCourse(@PathVariable(value="courseId") String courseId) {
		/**
		 * Returns a list of student IDs for the class if found by courseId
		 */
		System.out.println("+++++++++++++++++++ RegistrarController: GET Classes for courseId++++++++++++++++");
    	long courseIdNum = Long.parseLong(courseId);
    	
        ArrayList<Integer> students = registrars.get(courseIdNum);
        
    	if (students != null) {
    		return ResponseEntity.ok(students);
    	} else {
    		return ResponseEntity.notFound().build(); // 404 not found
    	}
	}
	
	// Drop student from course
	@DeleteMapping(value = "/registrars/{courseId}")
	public ResponseEntity dropStudentFromCourse(@PathVariable(value = "courseId") String courseId,
			@RequestParam(value = "studentId") String studentId) {
		System.out.println("+++++++++++++++++++ RegistrarController: Removing student ++++++++++++++++");
		int courseIdNum = Integer.parseInt(courseId); // registrar id
		int studentIdNum = Integer.parseInt(studentId);
		
        ArrayList<Integer> students = registrars.get(courseIdNum);
        if (students.contains(studentIdNum)) {
            int index = students.indexOf(studentIdNum);
            students.remove(index);
            registrars.put(courseIdNum, students);
        }

		return ResponseEntity.ok(students);
	}
	
    @PutMapping(value = "/registrars/{courseId}")
    public ResponseEntity addStudentToRegistrar(@PathVariable(value="courseId") String courseId, @RequestParam(value="studentId") String studentId) {
		System.out.println("+++++++++++++++++++ RegistrarController: Adding student to registrar ++++++++++++++++");
    	int courseIdNum = Integer.parseInt(courseId); // registrar id
    	int studentIdNum = Integer.parseInt(studentId);
    	ArrayList<Integer> itemToReturn = addEntry(courseIdNum, studentIdNum);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/")
                .buildAndExpand(courseIdNum).toUri();

        return ResponseEntity
        		.status(HttpStatus.OK)
        		.location(location)
        		.body(itemToReturn);
    }
}