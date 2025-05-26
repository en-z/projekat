package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.IshodIspitaDTO;
import org.projekat.mapper.IshodIspitaMapper;
import org.projekat.model.IshodIspita;
import org.projekat.model.Predmet;
import org.projekat.model.users.Nastavnik;
import org.projekat.model.users.Student;
import org.projekat.service.IshodIspitaService;
import org.projekat.service.PredmetService;
import org.projekat.service.users.NastavnikService;
import org.projekat.service.users.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ishodi")
@RequiredArgsConstructor
public class IshodIspitaController {

    private final IshodIspitaService service;
    private final StudentService studentService;
    private final PredmetService predmetService;
    private final NastavnikService nastavnikService;

    @GetMapping
    public ResponseEntity<List<IshodIspitaDTO>> getAll() {
        List<IshodIspitaDTO> dtos = service.findAll()
                .stream()
                .map(IshodIspitaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<IshodIspitaDTO> create(@RequestBody IshodIspitaDTO dto) {
        Student student = studentService.findById(dto.getStudentId()).orElseThrow();
        Predmet predmet = predmetService.findById(dto.getPredmetId()).orElseThrow();
        Nastavnik nastavnik = nastavnikService.findById(dto.getNastavnikId()).orElseThrow();

        IshodIspita ishod = IshodIspitaMapper.fromDTO(dto, student, predmet, nastavnik);
        IshodIspita saved = service.save(ishod);
        return ResponseEntity.ok(IshodIspitaMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/predmet/{id}")
    public ResponseEntity<List<IshodIspitaDTO>> getByPredmet(@PathVariable Long id) {
        List<IshodIspitaDTO> dtos = service.findAll().stream()
                .filter(i -> i.getPredmet().getId() == id)
                .map(IshodIspitaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<IshodIspitaDTO>> getByStudent(@PathVariable Long id) {
        List<IshodIspitaDTO> dtos = service.findAll().stream()
                .filter(i -> i.getStudent().getId() == id)
                .map(IshodIspitaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/predmet/{id}/unos-ocene-status")
    public ResponseEntity<Boolean> checkIfUnosOceneDozvoljen(@PathVariable Long id) {
        List<IshodIspita> ishodi = service.findAll().stream()
                .filter(i -> i.getPredmet().getId() == id)
                .toList();

        if (ishodi.isEmpty()) return ResponseEntity.ok(true); // ako nema unosa, dozvoli

        LocalDateTime najnovijiDatum = ishodi.stream()
                .map(i -> i.getInstrumentEvaluacije().getDatumOdrzavanja())
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.MIN);

        boolean dozvoljeno = service.canEnterGrade(najnovijiDatum);
        return ResponseEntity.ok(dozvoljeno);
    }

}
