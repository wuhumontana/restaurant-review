package com.ex.base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.ex.base.entity.Course;
import com.ex.base.jpa.CourseRepository;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    CourseRepository courseRepository;

    
    @Override
    public Course findById(Long id) {
        
        System.out.println("********************    " + "in CourseServiceImpl, findByID " + "id= " + id);
        Optional<Course> course = courseRepository.findById(id);
        
        System.out.println("%%%%%%%%%%%%%  " + "course  = " + course.toString());
        
        return course.orElse(null);
    }


    @Override
    public List<Course> getCourses() {
        System.out.println("********************    " + "in CourseServiceImpl, getCourses ");
        Optional<List<Course>> courses = courseRepository.getCourses();
        
        System.out.println("%%%%%%%%%%%%%  " + "courses  = " + courses);
        
        return courses.orElse(null);
    }
}
