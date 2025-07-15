package com.example.service.osoblje.controller;

import com.example.service.osoblje.dto.DokumentRequest;
import com.example.service.osoblje.models.Dokument;
import com.example.service.osoblje.service.DokumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/osoblje/dokument")
public class DokumentController {
    private final Path rootLocation = Paths.get("uploads");
    @Autowired
    private DokumentService dokumentService;
    @PostMapping("/upload")
    public ResponseEntity<Dokument> upload( @RequestBody DokumentRequest dokumentRequest ) {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            String filename = UUID.randomUUID() + "_" + dokumentRequest.getFile().getOriginalFilename();
            Path filePath = rootLocation.resolve(filename);
            Files.copy(dokumentRequest.getFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Dokument dokument = new Dokument();
            dokument.setStudentId(dokumentRequest.getStudentId());
            dokument.setNastavnikId(dokumentRequest.getNastavnikId());
            dokument.setNaslov(dokumentRequest.getNaslov());
            dokument.setOpis(dokument.getOpis());
            dokument.setDatum(LocalDate.now());
            dokument.setPath(filePath.toString());

            Dokument saved = dokumentService.save(dokument);
            return ResponseEntity.ok(saved);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Optional<Dokument> dokumentOpt = dokumentService.findById(id);

        if (dokumentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Dokument dokument = dokumentOpt.get();
        Path path = Paths.get(dokument.getPath());

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        try {
            Resource resource = new UrlResource(path.toUri());
            String filename = path.getFileName().toString();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public List<Dokument> getAll() {
        return dokumentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dokument> getById(@PathVariable Long id) {
        return dokumentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<List<Dokument>> getByStudentId(@PathVariable Long id) {
        return new ResponseEntity<>(dokumentService.findByStudentId(id),HttpStatus.OK);
    }
    @GetMapping("/nastavnik/{id}")
    public ResponseEntity<List<Dokument>> getByNastavnikId(@PathVariable Long id) {
        return new ResponseEntity<>(dokumentService.findByNastanvnikId(id),HttpStatus.OK);
    }
}
