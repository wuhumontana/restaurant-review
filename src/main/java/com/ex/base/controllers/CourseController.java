package com.ex.base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ex.base.entity.Course;
import com.ex.base.jpa.CourseRepository;
import com.ex.base.services.CourseService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CourseController {
    
	private List<Course> courses = new ArrayList();

	private final AtomicLong counter = new AtomicLong();

    @Autowired private CourseRepository repo;
    @Autowired private CourseService courseService;

	public CourseController() {
		// courses.add(new Course(1, 341, "Data Structures"));
		// courses.add(new Course(2, 202, "Object Oriented Design"));
	}

	@GetMapping(value = "/courses/")
	public ResponseEntity index() {

        System.out.println("+++++++++++++++++++ CourseController GET ++++++++++++++++");

        try {
            List<Course> courses = courseService.getCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 not found
        }
	}
	
    @GetMapping(value = "/courses/{id}")
    public ResponseEntity getCourseById(@PathVariable(value="id") Long id) {
        Course itemToReturn = null;
        for(Course course : courses){
            if(course.getId() == id)
                itemToReturn = course;
        }
        boolean matchExists = courses.stream()
        		.map(c -> c.getId())
        		.anyMatch(x->x==id);
       	if (matchExists) {
    		return ResponseEntity.ok(itemToReturn);
    	} else {
    		return ResponseEntity.notFound().build(); // 404 not found
    	}
    }
    
    @PostMapping(value = "/courses/")
    public ResponseEntity addToCourses(@RequestParam(value="number") String number, @RequestParam(value="title") String title) {
    	Long longNum = Long.parseLong(number);
        Course c = new Course(counter.incrementAndGet(), longNum, title);
        courses.add(c);
        repo.save(c); // Saves to database
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(c.getId()).toUri();

        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.location(location)
        		.body(c);
    }
    
    @PutMapping(value = "/courses/{id}")
    public ResponseEntity updateCourse(@PathVariable(value="id") Long id, 
    		@RequestParam(value="number") Long number, @RequestParam(value="title") String title) {
        courses.forEach(course ->  {
            if(course.getId() == id){
            	course.setNumber(number);
                course.setTitle(title);
            }
        });
        return ResponseEntity.ok(courses);
    }
    
    @DeleteMapping(value = "/courses/{id}")
    public ResponseEntity removeCourse(@PathVariable(value="id") Long id) {
        Course itemToRemove = null;
        for(Course course : courses){
            if(course.getId() == id)
                itemToRemove = course;
        }
        courses.remove(itemToRemove);
        return ResponseEntity.ok(courses);
    }
}