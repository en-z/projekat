package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.TerminDTO;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.entity.Termin;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import com.projekat.nastavnik_service.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/nastavnik/termini")
public class TerminController {
    @Autowired
    private TerminService terminService;
    @Autowired
    private NastavnikRepository nastavnikService;

    @GetMapping
    public ResponseEntity<List<TerminDTO>> getAll() {
        List<TerminDTO> dtos = terminService.findAll().stream()
                .map(this::toDTO)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerminDTO> getById(@PathVariable Long id) {
        return terminService.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/predmet/{predmetId}")
    public ResponseEntity<List<TerminDTO>> getByPredmet(@PathVariable Long predmetId) {
        List<TerminDTO> dtos = terminService.findByPredmetId(predmetId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<TerminDTO> create(@RequestBody TerminDTO dto) {
        Nastavnik nastavnik = nastavnikService.findById(dto.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nastavnik ne postoji"));

        Termin termin = new Termin();
        termin.setTema(dto.getTema());
        termin.setDatum(dto.getDatum());
        termin.setPredmetId(dto.getPredmetId());
        termin.setNastavnik(nastavnik);

        Termin saved = terminService.save(termin);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerminDTO> update(@PathVariable Long id, @RequestBody TerminDTO dto) {
        Optional<Termin> existing = terminService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Termin termin = existing.get();
        termin.setTema(dto.getTema());
        termin.setDatum(dto.getDatum());
        Termin saved = terminService.save(termin);

        return ResponseEntity.ok(toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (terminService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        terminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private TerminDTO toDTO(Termin t) {
        return new TerminDTO(t.getId(), t.getTema(), t.getDatum(),
                t.getPredmetId(), t.getNastavnik().getId());
    }
}
