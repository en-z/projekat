package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.AngazovanjaDTO;
import com.projekat.nastavnik_service.dto.PredmetDTO;
import com.projekat.nastavnik_service.service.AngazovanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nastavnik/angazovanja")
public class AngazovanjaController {
    @Autowired
    private AngazovanjaService angazovanjaService;
    @GetMapping
    public ResponseEntity<List<AngazovanjaDTO>> getAll(){
        return ResponseEntity.ok(angazovanjaService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AngazovanjaDTO> getById(@PathVariable long id){
        return  ResponseEntity.ok(angazovanjaService.findById(id));
    }
    @PostMapping
    public ResponseEntity<AngazovanjaDTO> post(@RequestBody AngazovanjaDTO dto){
        return ResponseEntity.ok(angazovanjaService.save(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(long id){
        angazovanjaService.deleteById(id);
        return ResponseEntity.ok("Izbrisan");
    }
    @GetMapping("/predmeti")
    public ResponseEntity<List<PredmetDTO>> getPredmeti(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);
        return ResponseEntity.ok(angazovanjaService.findPredmetiByUserId(id));
    }
}
