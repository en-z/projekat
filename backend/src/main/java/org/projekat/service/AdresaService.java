package org.projekat.service;

import lombok.RequiredArgsConstructor;
import org.projekat.model.Adresa;
import org.projekat.repository.AdresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdresaService {

    private final AdresaRepository adresaRepository;

    public Adresa save(Adresa adresa) {
        return adresaRepository.save(adresa);
    }

    public List<Adresa> findAll() {
        return adresaRepository.findAll();
    }

    public Optional<Adresa> findById(Long id) {
        return adresaRepository.findById(id);
    }

    public void deleteById(Long id) {
        adresaRepository.deleteById(id);
    }
}
