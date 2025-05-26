package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.model.ZavrsniRad;
import org.projekat.service.ZavrsniRadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zavrsniRadovi")
@RequiredArgsConstructor
public class ZavrsniRadController {

    private final ZavrsniRadService zavrsniRadService;

    @GetMapping
    public ResponseEntity<List<ZavrsniRad>> getAll() {
        return ResponseEntity.ok(zavrsniRadService.findAll());
    }

    @GetMapping("/mentor/{nastavnikId}")
    public ResponseEntity<List<ZavrsniRad>> getByMentor(@PathVariable Long nastavnikId) {
        return ResponseEntity.ok(zavrsniRadService.findByMentorId(nastavnikId));
    }
}

