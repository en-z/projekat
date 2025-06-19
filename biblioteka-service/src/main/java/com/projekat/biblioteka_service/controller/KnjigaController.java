package com.projekat.biblioteka_service.controller;

import com.projekat.biblioteka_service.entity.Knjiga;
import com.projekat.biblioteka_service.service.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biblioteka/knjiga")
public class KnjigaController {
    @Autowired
    private KnjigaService knjigaService;
    @GetMapping
    public List<Knjiga> getAll() {
        return knjigaService.getAll();
    }
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Knjiga> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(knjigaService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Knjiga> save(@RequestBody Knjiga knjiga) {
        return ResponseEntity.ok(knjigaService.save(knjiga));
    }

    @GetMapping("/kategorija/{kategorija}")
    public List<Knjiga> getByKategorija(@PathVariable String kategorija) {
        return knjigaService.getByKategorija(kategorija);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Knjiga>> pretraziStudente(
            @RequestParam(required = false) String naziv,
            @RequestParam(required = false) String kategorija,
            @RequestParam(required = false) String opis,
            @RequestParam(required = false) String autor
    ) {
        return ResponseEntity.ok(knjigaService.search(naziv, kategorija, opis, autor));
    }
}
