package org.projekat.controllers;

import org.projekat.model.Student;
import org.projekat.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/student/{id}")
    public CompletableFuture<ResponseEntity<Student>> getStudent(@PathVariable long id){
        return studentService.getStudent(id).thenApply(ResponseEntity::ok);
    }
    @PostMapping("/student/}")
    public CompletableFuture<ResponseEntity<Student>> postStudent(@RequestBody Student student){
        return studentService.saveStudent(student).thenApply(ResponseEntity::ok);
    }
}
