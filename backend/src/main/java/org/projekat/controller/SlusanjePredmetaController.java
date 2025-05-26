package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.SlusanjePredmetaDTO;
import org.projekat.mapper.SlusanjePredmetaMapper;
import org.projekat.model.Predmet;
import org.projekat.model.SlusanjePredmeta;
import org.projekat.model.users.Student;
import org.projekat.service.PredmetService;
import org.projekat.service.SlusanjePredmetaService;
import org.projekat.service.users.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slusanja")
@RequiredArgsConstructor
public class SlusanjePredmetaController {

    private final SlusanjePredmetaService service;
    private final StudentService studentService;
    private final PredmetService predmetService;

    @GetMapping("/predmet/{id}")
    public ResponseEntity<List<SlusanjePredmetaDTO>> getStudentiNaPredmetu(@PathVariable Long id) {
        List<SlusanjePredmetaDTO> dtos = service.findByPredmetId(id).stream()
                .map(SlusanjePredmetaMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<SlusanjePredmetaDTO>> getPredmetiStudenta(@PathVariable Long id) {
        List<SlusanjePredmetaDTO> dtos = service.findByStudentId(id).stream()
                .map(SlusanjePredmetaMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<SlusanjePredmetaDTO> upisiStudenta(@RequestBody SlusanjePredmetaDTO dto) {
        Student student = studentService.findById(dto.getStudent().getId()).orElseThrow();
        Predmet predmet = predmetService.findById(dto.getPredmetId()).orElseThrow();

        SlusanjePredmeta slusanje = SlusanjePredmetaMapper.fromDTO(dto, student, predmet);
        SlusanjePredmeta sacuvan = service.save(slusanje);

        return ResponseEntity.ok(SlusanjePredmetaMapper.toDTO(sacuvan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
