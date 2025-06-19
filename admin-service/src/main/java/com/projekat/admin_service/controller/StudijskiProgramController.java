package com.projekat.admin_service.controller;

import com.projekat.admin_service.DTO.StudijskiProgramDTO;
import com.projekat.admin_service.service.StudijskiProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    @RequestMapping("/api/admin/studijski-programi")
    public class StudijskiProgramController {

        @Autowired
        private StudijskiProgramService studijskiProgramService;

        @GetMapping
        public ResponseEntity<List<StudijskiProgramDTO>> getAll() {
            return ResponseEntity.ok(studijskiProgramService.getAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<StudijskiProgramDTO> getById(@PathVariable long id) {
            return ResponseEntity.ok(studijskiProgramService.getById(id));
        }

        @GetMapping("/by-fakultet")
        public ResponseEntity<List<StudijskiProgramDTO>> getByFakultetId(@RequestParam  long fakultetId) {
            return ResponseEntity.ok(studijskiProgramService.getByFakultetId(fakultetId));
        }

        @PostMapping
        public ResponseEntity<StudijskiProgramDTO> create(@RequestBody StudijskiProgramDTO dto) {
            StudijskiProgramDTO created = studijskiProgramService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }

        @PutMapping("/{id}")
        public ResponseEntity<StudijskiProgramDTO> update(@PathVariable long id, @RequestBody StudijskiProgramDTO dto) {
            return ResponseEntity.ok(studijskiProgramService.update(id, dto));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable long id) {
            studijskiProgramService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

