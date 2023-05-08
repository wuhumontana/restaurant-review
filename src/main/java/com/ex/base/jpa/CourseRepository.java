package com.ex.base.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ex.base.entity.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT c FROM Course c where c.id = ?1")
    Optional<Course> findCourseById(Long id);

    @Query(value = "SELECT c FROM Course c")
    Optional<List<Course>> getCourses();
}
