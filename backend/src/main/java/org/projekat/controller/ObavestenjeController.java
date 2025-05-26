package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.ObavestenjeDTO;
import org.projekat.model.Obavestenje;
import org.projekat.model.Predmet;
import org.projekat.model.users.Nastavnik;
import org.projekat.service.ObavestenjeService;
import org.projekat.service.PredmetService;
import org.projekat.service.users.NastavnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/obavestenja")
@RequiredArgsConstructor
public class ObavestenjeController {

    private final ObavestenjeService service;
    private final PredmetService predmetService;
    private final NastavnikService nastavnikService;

    @GetMapping
    public ResponseEntity<List<ObavestenjeDTO>> getAll() {
        List<ObavestenjeDTO> dtos = service.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObavestenjeDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/predmet/{id}")
    public ResponseEntity<List<ObavestenjeDTO>> getByPredmetId(@PathVariable Long id) {
        List<ObavestenjeDTO> dtos = service.findAll().stream()
                .filter(o -> o.getPredmet().getId() == (id))
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ObavestenjeDTO> create(@RequestBody ObavestenjeDTO dto) {
        Predmet predmet = predmetService.findById(dto.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));
        Nastavnik nastavnik = nastavnikService.findById(dto.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nastavnik ne postoji"));

        Obavestenje o = new Obavestenje();
        o.setNaslov(dto.getNaslov());
        o.setTekst(dto.getTekst());
        o.setDatumPostavljanja(LocalDateTime.now());
        o.setPredmet(predmet);
        o.setAutor(nastavnik);

        return ResponseEntity.ok(toDTO(service.save(o)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObavestenjeDTO> update(@PathVariable Long id, @RequestBody ObavestenjeDTO dto) {
        Optional<Obavestenje> existing = service.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Obavestenje o = existing.get();
        o.setNaslov(dto.getNaslov());
        o.setTekst(dto.getTekst());
        return ResponseEntity.ok(toDTO(service.save(o)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ObavestenjeDTO toDTO(Obavestenje o) {
        return new ObavestenjeDTO(o.getId(), o.getNaslov(), o.getTekst(),
                o.getDatumPostavljanja(), o.getPredmet().getId(), o.getAutor().getId());
    }
}
