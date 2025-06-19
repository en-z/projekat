package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.entity.NastavniMaterijal;
import com.projekat.nastavnik_service.repository.NastavniMaterijalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NastavniMaterijalService {
    @Autowired
    private NastavniMaterijalRepository nastavniMaterijalRepository;

    public List<NastavniMaterijal> getAll() {
        return nastavniMaterijalRepository.findAll();
    }

    public NastavniMaterijal getById(Long id) {
        return nastavniMaterijalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materijal not found"));
    }

    public NastavniMaterijal create(NastavniMaterijal materijal) {
        return nastavniMaterijalRepository.save(materijal);
    }

    public NastavniMaterijal update(Long id, NastavniMaterijal updated) {
        NastavniMaterijal existing = nastavniMaterijalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materijal not found"));
        existing.setOpis(updated.getOpis());
        existing.setUrl(updated.getUrl());
        existing.setPredmetId(updated.getPredmetId());
        return nastavniMaterijalRepository.save(existing);
    }

    public void delete(Long id) {
        if (!nastavniMaterijalRepository.existsById(id)) {
            throw new RuntimeException("Materijal not found");
        }
        nastavniMaterijalRepository.deleteById(id);
    }

    public List<NastavniMaterijal> findByPredmetId(Long predmetId) {

        return nastavniMaterijalRepository.findAllByPredmetId(predmetId);
    }
}
