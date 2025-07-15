package com.example.service.osoblje.service;

import com.example.service.osoblje.models.Inventar;
import com.example.service.osoblje.repository.InventarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarService {
    @Autowired
    public InventarRepository inventarRepository;
    public List<Inventar> findAll(Long id) {
        return inventarRepository.findByFakultetId(id);
    }

    public Optional<Inventar> findById(Long id) {
        return inventarRepository.findById(id);
    }

    public Inventar save(Inventar inventar) {
        return inventarRepository.save(inventar);
    }

    public void deleteById(Long id) {
        inventarRepository.deleteById(id);
    }
}
