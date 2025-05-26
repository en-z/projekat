package org.projekat.service;

import org.projekat.model.Predmet;
import org.projekat.repository.PredmetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PredmetService {

    private final PredmetRepository predmetRepository;

    public PredmetService(PredmetRepository predmetRepository) {
        this.predmetRepository = predmetRepository;
    }

    public List<Predmet> findAll() {
        return predmetRepository.findAll();
    }

    public Optional<Predmet> findById(Long id) {
        return predmetRepository.findById(id);
    }

    public Predmet save(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    public void deleteById(Long id) {
        predmetRepository.deleteById(id);
    }

    public List<Predmet> findByNaziv(String naziv) {
        return predmetRepository.findByNazivContainingIgnoreCase(naziv);
    }
}
