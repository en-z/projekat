package com.example.service.osoblje.service;

import com.example.service.osoblje.models.Dokument;
import com.example.service.osoblje.repository.DokumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DokumentService {
    @Autowired
    private DokumentRepository dokumentRepository;

    public List<Dokument> findAll() {
        return dokumentRepository.findAll();
    }

    public Page<Dokument> findByUserId(Long id, Pageable pageable) {
       return dokumentRepository.findByUserId(id,pageable);
    }
    public Optional<Dokument> findById(Long id) {
        return dokumentRepository.findById(id);
    }

    public Dokument save(Dokument dokument) {
        return dokumentRepository.save(dokument);
    }

    public void deleteById(Long id) {
        dokumentRepository.deleteById(id);
    }

    public Page<Dokument> getAllDokumenti(Pageable pageable) {
        return dokumentRepository.findAll(pageable);
    }
}
