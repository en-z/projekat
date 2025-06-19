package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.entity.ZavrsniRad;
import com.projekat.nastavnik_service.repository.ZavrsniRadRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/nastavnik/zavrsni/")
public class ZavrsniRadController {
    private ZavrsniRadRepository zavrsniRadService;

    @GetMapping
    public ResponseEntity<List<ZavrsniRad>> getAll() {
        return ResponseEntity.ok(zavrsniRadService.findAll());
    }

    @GetMapping("/mentor/{nastavnikId}")
    public ResponseEntity<List<ZavrsniRad>> getByMentor(@PathVariable Long nastavnikId) {
        return ResponseEntity.ok(zavrsniRadService.findByNastavnikId(nastavnikId));
    }
    @PostMapping
    public ResponseEntity<ZavrsniRad> create(@RequestBody ZavrsniRad zvanje){
        return ResponseEntity.ok(zavrsniRadService.save(zvanje));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ZavrsniRad> put(@PathVariable Long id){
        ZavrsniRad r =zavrsniRadService.findById(id).orElseThrow(()->new RuntimeException("error"));
        r.setStatus(1);
        return ResponseEntity.ok(zavrsniRadService.save(r));
    }
}
