package com.example.service.osoblje.service;

import com.example.service.osoblje.models.Kolokvijumi;
import com.example.service.osoblje.repository.KolokvijumiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class KolokvijumiService {
    @Autowired
    private KolokvijumiRepository kolokvijumiRepository;
    public List<Kolokvijumi> findAll() {
        return kolokvijumiRepository.findAll();
    }

    public Optional<Kolokvijumi> findById(Long id) {
        return kolokvijumiRepository.findById(id);
    }

    public Kolokvijumi save(Kolokvijumi kolokvijum) {
        return kolokvijumiRepository.save(kolokvijum);
    }

    public void deleteById(Long id) {
        kolokvijumiRepository.deleteById(id);
    }

    public List<Kolokvijumi> findByProgramId(Long programId) {
        return kolokvijumiRepository.findByProgramId(programId);
    }

    public List<Kolokvijumi> findByPredmetId(Long predmetId) {
        return kolokvijumiRepository.findByPredmetId(predmetId);
    }
}
