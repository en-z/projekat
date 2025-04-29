package org.projekat.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Student;
import org.projekat.repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Async
    public CompletableFuture<List<Student>> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return CompletableFuture.completedFuture(students);
    }
    @Async
    public CompletableFuture<Student> getStudent(long id){
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student nije pronadjen"));
        return CompletableFuture.completedFuture(student);
    }
    @Async
    public CompletableFuture<Student> saveStudent(Student student){
        return CompletableFuture.completedFuture(studentRepository.save(student));
    }
}
