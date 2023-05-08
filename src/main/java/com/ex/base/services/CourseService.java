package com.ex.base.services;

import java.util.List;

import com.ex.base.entity.Course;

public interface CourseService {
    Course findById(Long id);

    List<Course> getCourses();
}
