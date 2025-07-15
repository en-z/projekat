package com.projekat.admin_service.controller;

import com.projekat.admin_service.DTO.PredmetDTO;
import com.projekat.admin_service.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/predmeti")
public class PredmetController {

    @Autowired
    private PredmetService predmetService;

    @GetMapping
    public ResponseEntity<List<PredmetDTO>> getAll() {
        return ResponseEntity.ok(predmetService.getAll());
    }


    @GetMapping("/aktivni")
    public ResponseEntity<List<PredmetDTO>> getAktivni() {
        return ResponseEntity.ok(predmetService.getAktivne());
    }

    @GetMapping("/neaktivni")
    public ResponseEntity<List<PredmetDTO>> getNeaktivni() {
        return ResponseEntity.ok(predmetService.getNeaktivne());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredmetDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(predmetService.getById(id));
    }
    @PostMapping
    public ResponseEntity<PredmetDTO> create(@RequestBody PredmetDTO dto) {
        PredmetDTO created = predmetService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        predmetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-program")
    public ResponseEntity<List<PredmetDTO>> getByFakultetId(@RequestParam  long programId) {
        return ResponseEntity.ok(predmetService.findByFakultetId(programId));
    }
    @GetMapping("/by-ids")
    public ResponseEntity<List<PredmetDTO>> getByFakultetId(@RequestParam  List<Long> ids) {
        return ResponseEntity.ok(predmetService.findByIDS(ids));
    }
}

