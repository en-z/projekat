package org.projekat.service;

import org.projekat.model.ZavrsniRad;
import org.projekat.repository.ZavrsniRadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZavrsniRadService {

    private final ZavrsniRadRepository repo;

    public ZavrsniRadService(ZavrsniRadRepository repo) {
        this.repo = repo;
    }

    public List<ZavrsniRad> findAll() {
        return repo.findAll();
    }

    public Optional<ZavrsniRad> findById(Long id) {
        return repo.findById(id);
    }

    public ZavrsniRad save(ZavrsniRad rad) {
        return repo.save(rad);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<ZavrsniRad> findByMentorId(Long nastavnikId) {
        return repo.findAll().stream()
                .filter(rad -> rad.getMentor().getId() == nastavnikId)
                .toList();
    }
}
