package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.dto.ZavrsniRadDTO;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.entity.ZavrsniRad;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import com.projekat.nastavnik_service.repository.ZavrsniRadRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ZavrsniRadService {
    @Autowired
    private ZavrsniRadRepository repository;
    @Autowired
    private NastavnikRepository nastavnikRepository;
    public ResponseEntity<?>create(ZavrsniRadDTO dto){
        try {
            // Save the file to file system
            String uploadDir = "uploads/zavrsni-radovi/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + dto.getFile().getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(dto.getFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            ZavrsniRad zavrsniRad = new ZavrsniRad();
            zavrsniRad.setStudentId(dto.getStudentId());
            Nastavnik n = nastavnikRepository.findById(dto.getNastavnikId()).orElseThrow(()->new RuntimeException("Nema nastavnika"));
            zavrsniRad.setNastavnik(n);
            zavrsniRad.setNaslov(dto.getNaslov());
            zavrsniRad.setOpis(dto.getOpis());
            zavrsniRad.setFile(filePath.toString());
            zavrsniRad.setStatus(0);
            repository.save(zavrsniRad);

            return ResponseEntity.ok("Uspešno sačuvan završni rad.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška pri čuvanju fajla.");
        }
    }
}
