package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.entity.Obavestenje;
import com.projekat.nastavnik_service.repository.ObavjestenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObavjestenjaService {
    @Autowired
    private ObavjestenjeRepository obavjestenjeRepository;

    public List<Obavestenje> findAll() {
        return obavjestenjeRepository.findAll();
    }

    public Optional<Obavestenje> findById(Long id) {
        return obavjestenjeRepository.findById(id);
    }

    public Obavestenje save(Obavestenje obavestenje) {
        return obavjestenjeRepository.save(obavestenje);
    }

    public void deleteById(Long id) {
        obavjestenjeRepository.deleteById(id);

    }
}