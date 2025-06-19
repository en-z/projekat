package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.entity.NastavniMaterijal;
import com.projekat.nastavnik_service.service.NastavniMaterijalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nastavnik/materijali")
public class NastavniMaterijalController {

    @Autowired
    private NastavniMaterijalService nastavniMaterijalService;

    @GetMapping
    public ResponseEntity<List<NastavniMaterijal>> getAll() {
        return ResponseEntity.ok(nastavniMaterijalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NastavniMaterijal> getById(@PathVariable Long id) {
        return ResponseEntity.ok(nastavniMaterijalService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NastavniMaterijal> create(@RequestBody NastavniMaterijal materijal) {
        return ResponseEntity.ok(nastavniMaterijalService.create(materijal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NastavniMaterijal> update(@PathVariable Long id, @RequestBody NastavniMaterijal updated) {
        return ResponseEntity.ok(nastavniMaterijalService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nastavniMaterijalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/predmet/{predmetId}")
    public ResponseEntity<List<NastavniMaterijal>> findByPredmetId(@PathVariable Long predmetId) {
        return ResponseEntity.ok(nastavniMaterijalService.findByPredmetId(predmetId));
    }
}
