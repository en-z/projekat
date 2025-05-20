package org.projekat.service;

import org.projekat.model.Obavestenje;
import org.projekat.repository.ObavestenjeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObavestenjeService {

    private final ObavestenjeRepository obavestenjeRepository;

    public ObavestenjeService(ObavestenjeRepository obavestenjeRepository) {
        this.obavestenjeRepository = obavestenjeRepository;
    }

    public List<Obavestenje> findAll() {
        return obavestenjeRepository.findAll();
    }

    public Optional<Obavestenje> findById(Long id) {
        return obavestenjeRepository.findById(id);
    }

    public Obavestenje save(Obavestenje obavestenje) {
        return obavestenjeRepository.save(obavestenje);
    }

    public void deleteById(Long id) {
        obavestenjeRepository.deleteById(id);
    }
}
