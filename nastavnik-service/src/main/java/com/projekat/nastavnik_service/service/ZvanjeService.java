package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.dto.ZvanjeDTO;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.entity.Zvanje;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import com.projekat.nastavnik_service.repository.ZvanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZvanjeService {
    @Autowired
    private ZvanjeRepository zvanjeRepository;
    @Autowired
    private NastavnikRepository nastavnikRepository;

    public List<Zvanje> getAll() {
        return zvanjeRepository.findAll();
    }

    public Zvanje getById(Long id) {
        return zvanjeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zvanje not found"));
    }

    public Zvanje create(ZvanjeDTO zvanje) {
        Zvanje z = new Zvanje();
        Nastavnik n = nastavnikRepository.findById(zvanje.getNastavnikId()).orElseThrow(()->new RuntimeException("Error"));
        z.setDatumPrestanka(zvanje.getDatumPrestanka());
        z.setNastavnik(n);
        z.setDatumIzbora(zvanje.getDatumIzbora());
        z.setNaziv(zvanje.getNaziv());
        return zvanjeRepository.save(z);
    }

    public Zvanje update(Long id, Zvanje updatedZvanje) {
        Zvanje existing = zvanjeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zvanje not found"));

        existing.setNaziv(updatedZvanje.getNaziv());
        existing.setDatumIzbora(updatedZvanje.getDatumIzbora());
        existing.setDatumPrestanka(updatedZvanje.getDatumPrestanka());

        return zvanjeRepository.save(existing);
    }

    public void delete(Long id) {
        if (!zvanjeRepository.existsById(id)) {
            throw new RuntimeException("Zvanje not found");
        }
        zvanjeRepository.deleteById(id);
    }

    public List<Zvanje> findByNastavnikId(Long nastavnikId) {
        return zvanjeRepository.findAllByNastavnikId(nastavnikId);
    }
}
