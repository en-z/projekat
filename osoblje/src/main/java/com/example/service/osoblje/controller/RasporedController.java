package com.example.service.osoblje.controller;

import com.example.service.osoblje.models.Raspored;
import com.example.service.osoblje.service.RasporedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/osoblje/raspored")
public class RasporedController {
    @Autowired
    private RasporedService rasporedService;
    @GetMapping
    public List<Raspored> getAll() {
        return rasporedService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Raspored> getById(@PathVariable Long id) {
        return rasporedService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Raspored> create(@RequestBody Raspored raspored) {
        Raspored saved = rasporedService.save(raspored);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raspored> update(@PathVariable Long id, @RequestBody Raspored raspored) {
        return rasporedService.findById(id)
                .map(existing -> {
                    raspored.setId(id);
                    return ResponseEntity.ok(rasporedService.save(raspored));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (rasporedService.findById(id).isPresent()) {
            rasporedService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/program/{programId}")
    public List<Raspored> getByProgram(@PathVariable Long programId) {
        return rasporedService.findByProgramId(programId);
    }
}
