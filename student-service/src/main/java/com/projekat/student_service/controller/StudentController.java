package com.projekat.student_service.controller;

import com.projekat.student_service.client.NastavnikClient;
import com.projekat.student_service.dto.AddDTO;
import com.projekat.student_service.dto.StudentDTO;
import com.projekat.student_service.entity.Student;
import com.projekat.student_service.repository.StudentRepository;
import com.projekat.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/studenti")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private NastavnikClient nastavnikClient;
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<StudentDTO> getByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(studentService.getByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AddDTO dto) {
        Student created = studentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable long id, @RequestBody StudentDTO dto) {
        StudentDTO updated = studentService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
    @PutMapping("/prosecna")
    public ResponseEntity<?> update(@RequestParam long id, @RequestParam float ocena) {
        studentService.ocena(id,ocena);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> search(
            @RequestParam(required = false)  String ime,
            @RequestParam(required = false)  String prezime,
            @RequestParam(required = false)  String brojIndeksa,
            @RequestParam(required = false)  Integer godinaUpisa,
            @RequestParam(required = false)  Integer godinaStudija,
            @RequestParam(required = false)  Long studiskiId,
            @RequestParam(required = false)  Float prosekMin,
            @RequestParam(required = false)  Float prosekMax,
            @RequestParam(required = false)  Integer esbpMin,
            @RequestParam(required = false)  Integer esbpMax,
            @RequestParam(required = false)  Boolean aktivan,
            @RequestParam(required = false)  String ulica,
            @RequestParam(required = false)  String broj,
            @RequestParam(required = false)  String grad,
            @RequestParam(required = false)  String drzava
    ) {
        return ResponseEntity.ok(studentService.search(
                ime, prezime, brojIndeksa, godinaUpisa, godinaStudija,studiskiId,prosekMin,prosekMax,esbpMin,esbpMax,aktivan,ulica,broj,grad,drzava
        ));
    }
    @GetMapping("/ishod")
    public ResponseEntity<?> getIshode(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);
        Student s = studentRepository.findByUserId(id).orElseThrow(()->new RuntimeException("error"));
        return nastavnikClient.getIshode(s.getId());
    }
}