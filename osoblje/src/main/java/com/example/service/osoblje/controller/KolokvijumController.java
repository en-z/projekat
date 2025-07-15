package com.example.service.osoblje.controller;

import com.example.service.osoblje.models.Kolokvijumi;
import com.example.service.osoblje.service.KolokvijumiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/osoblje/kolokvijum")
public class KolokvijumController{
    @Autowired
    private KolokvijumiService kolokvijumiService;
    @GetMapping
    public List<Kolokvijumi> getAll() {
        return kolokvijumiService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kolokvijumi> getById(@PathVariable Long id) {
        return kolokvijumiService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Kolokvijumi> create(@RequestBody Kolokvijumi kolokvijum) {
        Kolokvijumi saved = kolokvijumiService.save(kolokvijum);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kolokvijumi> update(@PathVariable Long id, @RequestBody Kolokvijumi kolokvijum) {
        return kolokvijumiService.findById(id)
                .map(existing -> {
                    kolokvijum.setId(id);
                    return ResponseEntity.ok(kolokvijumiService.save(kolokvijum));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (kolokvijumiService.findById(id).isPresent()) {
            kolokvijumiService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/program/{programId}")
    public List<Kolokvijumi> getByProgramId(@PathVariable Long programId) {
        return kolokvijumiService.findByProgramId(programId);
    }

    @GetMapping("/predmet/{predmetId}")
    public List<Kolokvijumi> getByPredmetId(@PathVariable Long predmetId) {
        return kolokvijumiService.findByPredmetId(predmetId);
    }
}
