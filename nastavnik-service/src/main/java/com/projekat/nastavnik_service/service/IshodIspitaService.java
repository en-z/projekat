package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.entity.IshodIspita;
import com.projekat.nastavnik_service.repository.IshodIspitaRepository;
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
        List<IshodIspita> broj = repository.findAllByStudentIdAndPredmetId(ishod.getStudentId(),ishod.getPredmetId());
        int brojPokusaja = (broj== null || broj.isEmpty()) ? 1 : broj.size() + 1;
        ishod.setBrojPokusaja(brojPokusaja);
        return repository.save(ishod);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean canEnterGrade(LocalDateTime datumTermina) {
        return datumTermina.plusDays(15).isAfter(LocalDateTime.now());
    }
}
