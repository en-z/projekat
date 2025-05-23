package org.projekat.service;

import org.projekat.model.Nastavnik;
import org.projekat.repository.AngazovanjeRepository;
import org.projekat.repository.NastavnikRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NastavnikService {

    private final NastavnikRepository nastavnikRepository;
    private final AngazovanjeRepository angazovanjeRepository;

    public NastavnikService(NastavnikRepository nastavnikRepository, AngazovanjeRepository angazovanjeRepository) {
        this.nastavnikRepository = nastavnikRepository;
        this.angazovanjeRepository = angazovanjeRepository;
    }

    public List<Nastavnik> findAll() {
        return nastavnikRepository.findAll();
    }

    public Optional<Nastavnik> findById(Long id) {
        return nastavnikRepository.findById(id);
    }

    public Nastavnik save(Nastavnik nastavnik) {
        return nastavnikRepository.save(nastavnik);
    }

    public void deleteById(Long id) {
        angazovanjeRepository.deleteByNastavnikId(id);
        nastavnikRepository.deleteById(id);
    }


}

