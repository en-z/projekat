package org.projekat.service;

import org.projekat.model.IshodIspita;
import org.projekat.repository.IshodIspitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IshodIspitaService {

    private final IshodIspitaRepository repository;

    public IshodIspitaService(IshodIspitaRepository repository) {
        this.repository = repository;
    }

    public List<IshodIspita> findAll() {
        return repository.findAll();
    }

    public Optional<IshodIspita> findById(Long id) {
        return repository.findById(id);
    }

    public IshodIspita save(IshodIspita ishod) {
        return repository.save(ishod);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean canEnterGrade(LocalDateTime datumTermina) {
        return datumTermina.plusDays(15).isAfter(LocalDateTime.now());
    }
}