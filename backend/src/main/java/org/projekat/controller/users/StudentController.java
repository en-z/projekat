package org.projekat.controller.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.projekat.dto.users.StudentDTO;
import org.projekat.mapper.users.StudentMapper;
import org.projekat.model.users.Student;
import org.projekat.service.users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/studenti")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> pretraziStudente(
            @RequestParam(required = false) String ime,
            @RequestParam(required = false) String prezime,
            @RequestParam(required = false) String brojIndeksa,
            @RequestParam(required = false) Integer godinaUpisa,
            @RequestParam(required = false) Double minProsek,
            @RequestParam(required = false) Double maxProsek
    ) {
        List<Student> studenti = studentService.search(ime, prezime, brojIndeksa, godinaUpisa, minProsek, maxProsek);
        List<StudentDTO> dtos = studenti.stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}

