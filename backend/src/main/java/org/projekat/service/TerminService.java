package org.projekat.service;

import org.projekat.model.Termin;
import org.projekat.repository.TerminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerminService {

    private final TerminRepository terminRepository;

    public TerminService(TerminRepository terminRepository) {
        this.terminRepository = terminRepository;
    }

    public List<Termin> findAll() {
        return terminRepository.findAll();
    }

    public Optional<Termin> findById(Long id) {
        return terminRepository.findById(id);
    }

    public Termin save(Termin termin) {
        return terminRepository.save(termin);
    }

    public void deleteById(Long id) {
        terminRepository.deleteById(id);
    }

    public List<Termin> findByPredmetId(Long predmetId) {
        return terminRepository.findByPredmetId(predmetId);
    }

}

