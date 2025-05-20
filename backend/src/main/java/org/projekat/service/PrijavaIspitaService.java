package org.projekat.service;

import org.projekat.model.PrijavaIspita;
import org.projekat.repository.PrijavaIspitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrijavaIspitaService {

    private final PrijavaIspitaRepository repository;

    public PrijavaIspitaService(PrijavaIspitaRepository repository) {
        this.repository = repository;
    }

    public List<PrijavaIspita> findAll() {
        return repository.findAll();
    }

    public Optional<PrijavaIspita> findById(Long id) {
        return repository.findById(id);
    }

    public PrijavaIspita save(PrijavaIspita prijava) {
        return repository.save(prijava);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<PrijavaIspita> findByPredmetId(Long predmetId) {
        return repository.findByPredmetId(predmetId);
    }
}
