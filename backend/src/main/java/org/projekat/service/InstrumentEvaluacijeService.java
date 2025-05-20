package org.projekat.service;

import org.projekat.model.InstrumentEvaluacije;
import org.projekat.repository.InstrumentEvaluacijeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentEvaluacijeService {

    private final InstrumentEvaluacijeRepository repository;

    public InstrumentEvaluacijeService(InstrumentEvaluacijeRepository repository) {
        this.repository = repository;
    }

    public List<InstrumentEvaluacije> findAll() {
        return repository.findAll();
    }

    public Optional<InstrumentEvaluacije> findById(Long id) {
        return repository.findById(id);
    }

    public InstrumentEvaluacije save(InstrumentEvaluacije instrument) {
        return repository.save(instrument);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

