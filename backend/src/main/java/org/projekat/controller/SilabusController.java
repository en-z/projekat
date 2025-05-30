package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.SilabusDTO;
import org.projekat.model.Silabus;
import org.projekat.service.SilabusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/silabusi")
@RequiredArgsConstructor
public class SilabusController {


    private final SilabusService silabusService;

    @GetMapping("/{id}")
    public ResponseEntity<SilabusDTO> getById(@PathVariable Long id) {
        return silabusService.findById(id)
                .map(s -> new SilabusDTO(s.getId(), s.getSadrzaj(), s.getPredmet().getId(), s.getAutor().getId()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/predmet/{predmetId}")
    public ResponseEntity<SilabusDTO> getByPredmetId(@PathVariable Long predmetId) {
        return silabusService.findByPredmetId(predmetId)
                .map(s -> new SilabusDTO(s.getId(), s.getSadrzaj(), s.getPredmet().getId(), s.getAutor().getId()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SilabusDTO> create(@RequestBody SilabusDTO dto) {
        Silabus silabus = silabusService.create(dto.getPredmetId(), dto.getNastavnikId(), dto.getSadrzaj());
        return ResponseEntity.ok(new SilabusDTO(silabus.getId(), silabus.getSadrzaj(), silabus.getPredmet().getId(), silabus.getAutor().getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SilabusDTO> update(@PathVariable Long id, @RequestBody SilabusDTO dto) {
        Optional<Silabus> existing = silabusService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Silabus updated = silabusService.update(id, dto.getSadrzaj());
        return ResponseEntity.ok(new SilabusDTO(updated.getId(), updated.getSadrzaj(), updated.getPredmet().getId(), updated.getAutor().getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Silabus> existing = silabusService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        silabusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
