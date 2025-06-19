package com.projekat.admin_service.controller;

import com.projekat.admin_service.DTO.FakultetDTO;
import com.projekat.admin_service.service.FakultetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/fakulteti")
public class FakultetController {

    @Autowired
    private FakultetService fakultetService;

    // GET all fakulteti
    @GetMapping
    public ResponseEntity<List<FakultetDTO>> getAll() {
        return ResponseEntity.ok(fakultetService.getAll());
    }

    // GET fakultet by ID
    @GetMapping("/{id}")
    public ResponseEntity<FakultetDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(fakultetService.getById(id));
    }
    @PostMapping
    public void create(@RequestBody  FakultetDTO dto){
        fakultetService.create(dto);
    }
    // GET fakulteti by Univerzitet ID
    @GetMapping("/by-univerzitet")
    public ResponseEntity<List<FakultetDTO>> getByUniverzitetId(@RequestParam long univerzitetId) {
        return ResponseEntity.ok(fakultetService.getByUniverzitetId(univerzitetId));
    }

    // PUT update fakultet
    @PutMapping("/{id}")
    public ResponseEntity<FakultetDTO> update(@PathVariable long id, @RequestBody FakultetDTO dto) {
        return ResponseEntity.ok(fakultetService.update(id, dto));
    }

    // DELETE fakultet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        fakultetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

