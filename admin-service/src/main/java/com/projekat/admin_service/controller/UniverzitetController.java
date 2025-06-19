package com.projekat.admin_service.controller;

import com.projekat.admin_service.DTO.UniverzitetDto;
import com.projekat.admin_service.service.UniverzitetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/univerziteti")
public class UniverzitetController {

    @Autowired
    private UniverzitetService univerzitetService;

    // GET all univerziteti
    @GetMapping
    public ResponseEntity<List<UniverzitetDto>> getAll() {
        return ResponseEntity.ok(univerzitetService.getAll());
    }

    // GET univerzitet by ID
    @GetMapping("/{id}")
    public ResponseEntity<UniverzitetDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(univerzitetService.getById(id));
    }
    @PostMapping
    public ResponseEntity<?>create(@RequestBody UniverzitetDto dto){
       return ResponseEntity.ok(univerzitetService.create(dto));
    }
    // PUT update univerzitet
    @PutMapping("/{id}")
    public ResponseEntity<UniverzitetDto> update(@PathVariable long id, @RequestBody UniverzitetDto dto) {
        return ResponseEntity.ok(univerzitetService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        univerzitetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
