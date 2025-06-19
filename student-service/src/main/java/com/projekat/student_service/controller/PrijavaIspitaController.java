package com.projekat.student_service.controller;

import com.projekat.student_service.client.AdminClient;
import com.projekat.student_service.dto.PredmetDTO;
import com.projekat.student_service.dto.PrijavaIspitaDTO;
import com.projekat.student_service.service.PrijavaIspitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student/prijave-ispita")
public class PrijavaIspitaController {

    @Autowired
    private PrijavaIspitaService prijavaIspitaService;
    @Autowired
    private AdminClient adminClient;

    @GetMapping
    public ResponseEntity<List<PrijavaIspitaDTO>> getAll() {
        return ResponseEntity.ok(prijavaIspitaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrijavaIspitaDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(prijavaIspitaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PrijavaIspitaDTO> create(@RequestBody PrijavaIspitaDTO dto) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);
        PrijavaIspitaDTO created = prijavaIspitaService.create(id,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrijavaIspitaDTO> update(@PathVariable long id, @RequestBody PrijavaIspitaDTO dto) {
        dto.setId(id);
        PrijavaIspitaDTO updated = prijavaIspitaService.put(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        prijavaIspitaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/predmeti")
    public List<PredmetDTO> predmetiZaPrijavu(){
        String userId= (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id= Long.parseLong(userId);
        return prijavaIspitaService.getPredmete(id);
    }
}
