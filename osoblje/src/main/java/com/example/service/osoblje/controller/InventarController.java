package com.example.service.osoblje.controller;

import com.example.service.osoblje.models.Inventar;
import com.example.service.osoblje.service.InventarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/osoblje/inventar")
public class InventarController {
    @Autowired
    private InventarService inventarService;

    @GetMapping("{id}")
    public List<Inventar> getAll(@PathVariable Long id) {
        return inventarService.findAll(id);
    }

    @PostMapping
    public Inventar create(@RequestBody Inventar inventar) {
        return inventarService.save(inventar);
    }

    @PutMapping("/{id}/batch")
    public ResponseEntity<List<Inventar>> update(@PathVariable Long id, @RequestBody List<Inventar> updated) {
        return ResponseEntity.ok(inventarService.updateExisting(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (inventarService.findById(id).isPresent()) {
            inventarService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
