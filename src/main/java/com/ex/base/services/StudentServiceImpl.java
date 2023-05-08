package com.ex.base.services;

import com.ex.base.entity.Student;
import com.ex.base.jpa.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired StudentRepository studentRepository;

    @Override
    public Student findById(Long id) {
        Optional<Student> result = studentRepository.findStudentById(id);
        return result.get();
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<Student>();
        studentRepository.findAll().iterator().forEachRemaining(students::add);
        return students;
    }
}
