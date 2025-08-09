package com.example.service.osoblje.service;

import com.example.service.osoblje.models.Inventar;
import com.example.service.osoblje.repository.InventarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Inventar> updateExisting(List<Inventar> lista) {
        List<Inventar> updated = new ArrayList<>();

        for (Inventar item : lista) {
            if (item.getId() == null) continue;
            Optional<Inventar> existingOpt = inventarRepository.findById(item.getId());
            if (existingOpt.isPresent()) {
                Inventar existing = existingOpt.get();
                existing.setKolicina(item.getKolicina());
                existing.setNaziv(item.getNaziv());
                existing.setOpis(item.getOpis());
                updated.add(existing);
            }
        }

        return inventarRepository.saveAll(updated);
    }
}
