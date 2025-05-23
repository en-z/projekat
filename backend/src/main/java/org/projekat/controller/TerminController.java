package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.TerminDTO;
import org.projekat.model.Nastavnik;
import org.projekat.model.Predmet;
import org.projekat.model.Termin;
import org.projekat.service.PredmetService;
import org.projekat.service.TerminService;
import org.projekat.service.NastavnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/termini")
@RequiredArgsConstructor
public class TerminController {

    private final TerminService terminService;
    private final PredmetService predmetService;
    private final NastavnikService nastavnikService;

    // GET all
    @GetMapping
    public ResponseEntity<List<TerminDTO>> getAll() {
        List<TerminDTO> dtos = terminService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<TerminDTO> getById(@PathVariable Long id) {
        return terminService.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET by predmet
    @GetMapping("/predmet/{predmetId}")
    public ResponseEntity<List<TerminDTO>> getByPredmet(@PathVariable Long predmetId) {
        List<TerminDTO> dtos = terminService.findByPredmetId(predmetId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TerminDTO> create(@RequestBody TerminDTO dto) {
        Predmet predmet = predmetService.findById(dto.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));
        Nastavnik nastavnik = nastavnikService.findById(dto.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nastavnik ne postoji"));

        Termin termin = new Termin();
        termin.setTema(dto.getTema());
        termin.setDatum(dto.getDatum());
        termin.setPredmet(predmet);
        termin.setNastavnik(nastavnik);

        Termin saved = terminService.save(termin);
        return ResponseEntity.ok(toDTO(saved));
    }

    // UPDATE
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

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (terminService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        terminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping helper
    private TerminDTO toDTO(Termin t) {
        return new TerminDTO(t.getId(), t.getTema(), t.getDatum(),
                t.getPredmet().getId(), t.getNastavnik().getId());
    }
}
