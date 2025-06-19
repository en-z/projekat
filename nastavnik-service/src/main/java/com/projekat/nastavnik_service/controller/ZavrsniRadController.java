package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.ZavrsniRadDTO;
import com.projekat.nastavnik_service.entity.ZavrsniRad;
import com.projekat.nastavnik_service.service.ZavrsniRadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/nastavnik/zavrsni")
public class ZavrsniRadController {

    @Autowired
    private ZavrsniRadService service;
    @GetMapping
    public ResponseEntity<List<ZavrsniRad>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}/download")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        ZavrsniRad rad = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Završni rad nije pronađen"));

        // Provera da li fajl postoji na putanji iz zavrsnog rada
        Path filePath = Paths.get(rad.getFile());
        if (!Files.exists(filePath)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
            // Vracanje fajla sa date putanje
            Resource resource = new UrlResource(filePath.toUri());

            String fileName = filePath.getFileName().toString();

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/mentor/{nastavnikId}")
    public ResponseEntity<List<ZavrsniRad>> getByMentor(@PathVariable Long nastavnikId) {
        return ResponseEntity.ok(service.getByNastavnikUserId(nastavnikId));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> create(@ModelAttribute ZavrsniRadDTO dto) {
        try {
            service.create(dto);
            return ResponseEntity.ok("Uspešno kreirano.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška pri ažuriranju fajla.");
        }
    }

    @PutMapping(name="/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute ZavrsniRadDTO dto) {
        try {
            ZavrsniRad updated = service.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška pri ažuriranju fajla.");
        }
    }
}