package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.ZavrsniRadDTO;
import com.projekat.nastavnik_service.entity.ZavrsniRad;
import com.projekat.nastavnik_service.repository.ZavrsniRadRepository;
import com.projekat.nastavnik_service.service.ZavrsniRadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@RestController
@RequestMapping("/api/nastavnik/zavrsni/")
public class ZavrsniRadController {
    @Autowired
    private ZavrsniRadRepository zavrsniRadService;
    @Autowired
    private ZavrsniRadService service;
    @GetMapping
    public ResponseEntity<List<ZavrsniRad>> getAll() {
        return ResponseEntity.ok(zavrsniRadService.findAll());
    }
    @GetMapping("/{id}/download")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        ZavrsniRad rad = zavrsniRadService.findById(id)
                .orElseThrow(() -> new RuntimeException("Završni rad nije pronađen"));

        Path filePath = Paths.get(rad.getFile());
        if (!Files.exists(filePath)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
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
        return ResponseEntity.ok(zavrsniRadService.findByNastavnikId(nastavnikId));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ZavrsniRadDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ZavrsniRad> put(@PathVariable Long id){
        ZavrsniRad r =zavrsniRadService.findById(id).orElseThrow(()->new RuntimeException("error"));
        r.setStatus(1);
        return ResponseEntity.ok(zavrsniRadService.save(r));
    }
}
