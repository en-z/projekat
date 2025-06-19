package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.dto.ZavrsniRadDTO;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.entity.ZavrsniRad;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import com.projekat.nastavnik_service.repository.ZavrsniRadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ZavrsniRadService {

    @Autowired
    private ZavrsniRadRepository repository;

    @Autowired
    private NastavnikRepository nastavnikRepository;

    public Optional<ZavrsniRad> findById(Long id) {
        return repository.findById(id);
    }

    public List<ZavrsniRad> getAll() {
        return repository.findAll();
    }

    public List<ZavrsniRad> getByNastavnikUserId(Long nastavnikUserId) {
        return repository.findByNastavnikUserId(nastavnikUserId);
    }

    public void create(ZavrsniRadDTO dto) throws IOException {
        // Pravljenje foldera uploads/zavrsni-radovi ako ne postoji
        String uploadDir = "uploads/zavrsni-radovi/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Cuvanje fajla u folderu
        String fileName = System.currentTimeMillis() + "_" + dto.getFile().getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(dto.getFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Pravljenje i cuvanje zavrsnog rada
        ZavrsniRad zavrsniRad = new ZavrsniRad();
        zavrsniRad.setStudentId(dto.getStudentId());
        Nastavnik n = nastavnikRepository.findById(dto.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nema nastavnika"));
        zavrsniRad.setNastavnik(n);
        zavrsniRad.setNaslov(dto.getNaslov());
        zavrsniRad.setOpis(dto.getOpis());
        zavrsniRad.setFile(filePath.toString());
        repository.save(zavrsniRad);
    }

    public ZavrsniRad update(Long id, ZavrsniRadDTO dto) throws IOException {
        // Provera da li zavrsni rad sa datim id postoji
        ZavrsniRad r = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Završni rad nije pronađen"));

        // Opcionalan update fajla
        if (dto.getFile() != null && !dto.getFile().isEmpty()) {
            String uploadDir = "uploads/zavrsni-radovi/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + dto.getFile().getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(dto.getFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            r.setFile(filePath.toString());
        }

        // Opcionalan update ostalih atributa
        if (dto.getNaslov() != null) r.setNaslov(dto.getNaslov());
        if (dto.getOpis() != null) r.setOpis(dto.getOpis());
        if (dto.getStudentId() != 0) r.setStudentId(dto.getStudentId());

        if (dto.getNastavnikId() != 0) {
            Nastavnik n = nastavnikRepository.findById(dto.getNastavnikId())
                    .orElseThrow(() -> new RuntimeException("Nastavnik nije pronađen"));
            r.setNastavnik(n);
        }

        return repository.save(r);
    }
}