package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.ZavrsniRadDTO;
import com.projekat.nastavnik_service.entity.ZavrsniRad;
import com.projekat.nastavnik_service.service.ZavrsniRadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nastavnik/zavrsni")
public class ZavrsniRadController {

    @Autowired
    private ZavrsniRadService service;

    @GetMapping
    public ResponseEntity<List<ZavrsniRad>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/mentor/{nastavnikUserId}")
    public ResponseEntity<List<ZavrsniRad>> getByMentor(@PathVariable Long nastavnikUserId) {
        return ResponseEntity.ok(service.getByNastavnikUserId(nastavnikUserId));
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

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @ModelAttribute ZavrsniRadDTO dto) {
        try {
            ZavrsniRad updated = service.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška pri ažuriranju fajla.");
        }
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestBody String filePathStr) {
        try {
            Path filePath = Paths.get(filePathStr).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}