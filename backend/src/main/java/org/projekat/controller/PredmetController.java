package org.projekat.controller;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<Predmet>> getAll() {
        return ResponseEntity.ok(predmetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Predmet> getById(@PathVariable Long id) {
        return predmetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

