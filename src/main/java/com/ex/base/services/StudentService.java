package com.ex.base.services;

import java.util.List;

import com.ex.base.entity.Student;

public interface StudentService {
    Student findById(Long id);
    List<Student> getStudents();
}
