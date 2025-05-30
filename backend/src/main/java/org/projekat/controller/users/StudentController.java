package org.projekat.controller.users;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.PrijavaIspitaDTO;
import org.projekat.dto.users.StudentDTO;
import org.projekat.jwt.CustomUserDetails;
import org.projekat.mapper.users.StudentMapper;
import org.projekat.model.IshodIspita;
import org.projekat.model.Student;
import org.projekat.service.StudentService;
import org.projekat.service.users.StudentServiceDusan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/studenti")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceDusan studentService;
    @Autowired
    private StudentService studentServiceo;
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
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    @GetMapping("/predmeti-za-upis")
    public ResponseEntity<?> getPredmeteZaUpis(Authentication authentication) throws Exception{
        long studentId = extractUserIdFromAuth();
        return studentServiceo.getPredmeteZaUpis(studentId).get();
    }

    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    @GetMapping("/ishod-ispita")
    public ResponseEntity<List<IshodIspita>> getIshodIspita(Authentication authentication)throws Exception {
        long studentId = extractUserIdFromAuth();
        return studentServiceo.getIshodIspita(studentId)
                .thenApply(ResponseEntity::ok).get();
    }

    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    @PostMapping("/prijava-ispita")
    public ResponseEntity<Object> prijaviIspit(@RequestBody PrijavaIspitaDTO dto, Authentication authentication)throws Exception {
        long studentId = extractUserIdFromAuth();
        return studentServiceo.prijaviIspit(dto, studentId)
                .thenApply(ResponseEntity::new).get();
    }

    private long extractUserIdFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}

