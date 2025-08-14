package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.SilabusDTO;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.entity.Silabus;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import com.projekat.nastavnik_service.service.SilabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nastavnik/silabusi")
public class SilabusController {
    @Autowired
    private SilabusService silabusService;
    @Autowired
    private NastavnikRepository nastavnikRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SilabusDTO> getById(@PathVariable Long id) {
        return silabusService.findById(id)
                .map(s -> new SilabusDTO(s.getId(), s.getSadrzaj(), s.getPredmetId(), s.getAutor().getId()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/predmet/{id}")
    public ResponseEntity<SilabusDTO> getByPredmetId(@PathVariable long id) {
         Silabus s = silabusService.findByPredmetId(id);
         SilabusDTO dto = toDto(s);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/predmeti/{predmetId}")
    public ResponseEntity<List<SilabusDTO>> getByAllPredmetId(@PathVariable long predmetId) {
        List<SilabusDTO> l = silabusService.findAllByPredmetId(predmetId).stream().map(f->SilabusController.toDto(f)).toList();
        return ResponseEntity.ok(l);
    }
    @PostMapping
    public ResponseEntity<SilabusDTO> create(@RequestBody SilabusDTO dto) {
        Silabus silabus = new Silabus();
        silabus.setSadrzaj(dto.getSadrzaj());
        silabus.setPredmetId(dto.getPredmetId());
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long uid = Long.parseLong(userId);
        Nastavnik n = nastavnikRepository.findById(uid).orElseThrow(()->new RuntimeException("Error"));
        silabus.setAutor(n);
        silabus= silabusService.create(silabus);
        dto.setId(silabus.getId());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SilabusDTO> update(@PathVariable Long id, @RequestBody SilabusDTO dto) {
        Optional<Silabus> existing = silabusService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Silabus updated = silabusService.update(id, dto.getSadrzaj());
        dto.setSadrzaj(updated.getSadrzaj());
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Silabus> existing = silabusService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        silabusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private static SilabusDTO toDto(Silabus s ){
        SilabusDTO dto = new SilabusDTO(s.getId(),s.getSadrzaj(),s.getPredmetId(),s.getAutor().getId());
        return dto;
    }
}
