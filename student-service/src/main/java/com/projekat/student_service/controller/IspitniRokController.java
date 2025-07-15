package com.projekat.student_service.controller;

import com.projekat.student_service.entity.IspitniRok;
import com.projekat.student_service.service.IspitniRokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/ispitni-rokovi")
public class IspitniRokController {

    @Autowired
    private IspitniRokService ispitniRokService;

    @GetMapping
    public ResponseEntity<List<IspitniRok>> getAll() {
        return ResponseEntity.ok(ispitniRokService.getAll());
    }

    @GetMapping("/aktivni")
    public ResponseEntity<List<IspitniRok>> getAktivne() {
        return ResponseEntity.ok(ispitniRokService.getAktivne());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IspitniRok> getById(@PathVariable long id) {
        return ResponseEntity.ok(ispitniRokService.getById(id));
    }

    @PostMapping
    public ResponseEntity<IspitniRok> create(@RequestBody IspitniRok ispitniRok) {
        IspitniRok created = ispitniRokService.create(ispitniRok);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IspitniRok> update(@PathVariable long id, @RequestBody IspitniRok ispitniRok) {
        IspitniRok updated = ispitniRokService.update(id, ispitniRok);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        ispitniRokService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
