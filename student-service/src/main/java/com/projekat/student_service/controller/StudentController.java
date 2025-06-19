package com.projekat.student_service.controller;

import com.projekat.student_service.dto.StudentDTO;
import com.projekat.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/studenti")
public class StudentController {

    @Autowired
    private StudentService studentService;

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
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        StudentDTO created = studentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable long id, @RequestBody StudentDTO dto) {
        StudentDTO updated = studentService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> search(
            @RequestParam(required = false) String ime,
            @RequestParam(required = false) String prezime,
            @RequestParam(required = false) String brojIndeksa,
            @RequestParam(required = false) Integer godinaUpisa,
            @RequestParam(defaultValue = "0") float minProsek,
            @RequestParam(defaultValue = "10") float maxProsek
    ) {
        return ResponseEntity.ok(studentService.search(
                ime, prezime, brojIndeksa, godinaUpisa, minProsek, maxProsek
        ));
    }
}