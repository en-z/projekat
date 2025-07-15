package com.example.service.osoblje.service;

import com.example.service.osoblje.models.Dokument;
import com.example.service.osoblje.repository.DokumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Dokument> findByNastanvnikId(Long id) {
       return dokumentRepository.findByNastavnikId(id);
    }
    public List<Dokument>findByStudentId(Long id){
        return dokumentRepository.findByStudentId(id);
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
}
