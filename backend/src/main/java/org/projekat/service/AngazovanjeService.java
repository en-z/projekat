package org.projekat.service;

import org.projekat.model.Angazovanje;
import org.projekat.model.Predmet;
import org.projekat.repository.AngazovanjeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AngazovanjeService {

    private final AngazovanjeRepository angazovanjeRepository;

    public AngazovanjeService(AngazovanjeRepository angazovanjeRepository) {
        this.angazovanjeRepository = angazovanjeRepository;
    }

    public List<Angazovanje> findAll() {
        return angazovanjeRepository.findAll();
    }

    public Optional<Angazovanje> findById(Long id) {
        return angazovanjeRepository.findById(id);
    }

    public Angazovanje save(Angazovanje angazovanje) {
        return angazovanjeRepository.save(angazovanje);
    }

    public void deleteById(Long id) {
        angazovanjeRepository.deleteById(id);
    }

    public List<Predmet> findPredmetiByUserId(Long userId) {
        return angazovanjeRepository.findByUserId(userId)
                .stream()
                .map(Angazovanje::getPredmet)
                .collect(Collectors.toList());
    }
}
