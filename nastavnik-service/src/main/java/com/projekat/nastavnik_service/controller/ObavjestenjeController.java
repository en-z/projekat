package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.ObavestenjeDTO;
import com.projekat.nastavnik_service.entity.Obavestenje;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import com.projekat.nastavnik_service.service.NastavnikService;
import com.projekat.nastavnik_service.service.ObavjestenjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/nastavnik/obavestenja")
public class ObavjestenjeController{
    @Autowired
    private ObavjestenjaService service;
    @Autowired
    private NastavnikRepository nastavnikService;

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
                .filter(o -> o.getPredmetId() == (id))
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ObavestenjeDTO> create(@RequestBody ObavestenjeDTO dto) {
        Obavestenje o = new Obavestenje();
        o.setNaslov(dto.getNaslov());
        o.setTekst(dto.getTekst());
        o.setDatum(LocalDateTime.now());
        o.setPredmetId(dto.getPredmetId());
        o.setAutor(nastavnikService.findById(dto.getNastavnikId()).orElseThrow(()->new RuntimeException("error")));

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
                o.getDatum(), o.getPredmetId(), o.getAutor().getId(),o.getAutor().getIme(),o.getAutor().getPrezime());
    }
}


