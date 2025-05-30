package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.PredmetDTO;
import org.projekat.model.Predmet;
import org.projekat.service.PredmetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmeti")
@RequiredArgsConstructor
public class PredmetController {

    private final PredmetService predmetService;

    @GetMapping
    public ResponseEntity<List<PredmetDTO>> getAll() {
        List<PredmetDTO> dtos = predmetService.findAll().stream()
                .map(p -> new PredmetDTO(p.getId(), p.getNaziv(), p.getEsbp()))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Predmet> getById(@PathVariable Long id) {
        return predmetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

