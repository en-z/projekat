package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.IshodIspitaDTO;
import com.projekat.nastavnik_service.entity.IshodIspita;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.mapper.IshodIspitaMapper;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import com.projekat.nastavnik_service.service.IshodIspitaService;
import com.projekat.nastavnik_service.service.UploadXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/nastavnik/ishodi")
public class IshodIspitaController {
    @Autowired
    private IshodIspitaService service;
    @Autowired
    private NastavnikRepository nastavnikService;

    @GetMapping
    public ResponseEntity<List<IshodIspitaDTO>> getAll() {
        List<IshodIspitaDTO> dtos = service.findAll()
                .stream()
                .map(IshodIspitaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<IshodIspitaDTO> create(@RequestBody IshodIspitaDTO dto) {
        Nastavnik nastavnik = nastavnikService.findById(dto.getNastavnikId()).orElseThrow();

        IshodIspita ishod = IshodIspitaMapper.fromDTO(dto, nastavnik);
        IshodIspita saved = service.save(ishod);
        return ResponseEntity.ok(IshodIspitaMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/predmet/{id}")
    public ResponseEntity<List<IshodIspitaDTO>> getByPredmet(@PathVariable Long id) {
        List<IshodIspitaDTO> dtos = service.findAll().stream()
                .filter(i -> i.getPredmetId() == id)
                .map(IshodIspitaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<IshodIspitaDTO>> getByStudent(@PathVariable Long id) {
        List<IshodIspitaDTO> dtos = service.findAll().stream()
                .filter(i -> i.getStudentId() == id)
                .map(IshodIspitaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/predmet/{id}/unos-ocene-status")
    public ResponseEntity<Boolean> checkIfUnosOceneDozvoljen(@PathVariable Long id) {
        List<IshodIspita> ishodi = service.findAll().stream()
                .filter(i -> i.getPredmetId() == id)
                .toList();

        if (ishodi.isEmpty()) return ResponseEntity.ok(true); // ako nema unosa, dozvoli

        LocalDateTime najnovijiDatum = ishodi.stream()
                .map(i -> i.getInstrumentEvaluacije().getDatumOdrzavanja())
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.MIN);

        boolean dozvoljeno = service.canEnterGrade(najnovijiDatum);
        return ResponseEntity.ok(dozvoljeno);
    }
    @Autowired
    private UploadXmlService uploadXmlService;

    @PostMapping("/xml-file")
    public CompletableFuture<ResponseEntity<?>> uploadXmlFile(@RequestParam("file") MultipartFile file) {
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            return uploadXmlService.uploadXmlFromString(content);
        } catch (IOException e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("File read error: " + e.getMessage()));
        }
    }

    @PostMapping("/xml-string")
    public CompletableFuture<ResponseEntity<?>> uploadXmlString(@RequestBody String xmlContent) {
        return uploadXmlService.uploadXmlFromString(xmlContent);
    }
}
