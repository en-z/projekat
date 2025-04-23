package org.projekat.controllers;

import com.sun.istack.NotNull;
import org.projekat.model.Student;
import org.projekat.repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/studenti")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping
    public List<Student> getAllStudenti(){
        return (List<Student>)studentRepository.findAll();
    }
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable long id){
        return studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student nije pronadjen"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }
    @PutMapping("/{id}")
    public Student putStudent(@PathVariable long id,@RequestBody Student newStudent){
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student nije pronadjen"));
        student.setOsoba(newStudent.getOsoba());
        student.setBrojIndeksa(newStudent.getBrojIndeksa());
        student.setOsvojeniESPB(newStudent.getOsvojeniESPB());
        student.setProsecnaOcena(newStudent.getProsecnaOcena());
        return studentRepository.save(student);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable long id){
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student nije pronadjen"));
        studentRepository.delete(student);    }
}
