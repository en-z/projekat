package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.NastavnikCreateDTO;
import com.projekat.nastavnik_service.dto.NastavnikDTO;
import com.projekat.nastavnik_service.dto.PredmetDTO;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.mapper.users.NastavnikMapper;
import com.projekat.nastavnik_service.service.AngazovanjaService;
import com.projekat.nastavnik_service.service.NastavnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nastavnik/nastavnici")
public class NastavnikController {
    @Autowired
    private NastavnikService nastavnikService;
    @Autowired
    private AngazovanjaService angazovanjeService;

    @GetMapping
    public ResponseEntity<List<NastavnikDTO>> getAll() {
        return ResponseEntity.ok(nastavnikService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NastavnikDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(nastavnikService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NastavnikCreateDTO> create(@RequestBody NastavnikCreateDTO dto) {
        nastavnikService.create(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NastavnikDTO> update(@PathVariable Long id, @RequestBody NastavnikDTO dto) {
        dto.setId(id);
        nastavnikService.put(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nastavnikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //    @GetMapping("/{id}/predmeti")
//    public ResponseEntity<List<Predmet>> getPredmetiZaNastavnika(@PathVariable Long id) {
//        return ResponseEntity.ok(angazovanjeService.findPredmetiByUserId(id));
//    }
    @GetMapping("/{id}/predmeti")
    public ResponseEntity<List<PredmetDTO>> getPredmetiZaNastavnika(@PathVariable Long id) {
        List<PredmetDTO> dtos = angazovanjeService.findPredmetiByUserId(id);
        return ResponseEntity.ok(dtos);
    }
}
