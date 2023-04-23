package com.michael.springbootreactauthenticationjwt.service;

import com.michael.springbootreactauthenticationjwt.model.Student;
import com.michael.springbootreactauthenticationjwt.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(String id){
        return studentRepository.findById(id).get();
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }


    public long studentCount(){
        return studentRepository.count();
    }

}
