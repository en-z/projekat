package com.example.service.osoblje.service;

import com.example.service.osoblje.models.Obavjestenja;
import com.example.service.osoblje.repository.ObavjestenjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObavjestenjaService{
    @Autowired
    private ObavjestenjaRepository obavjestenjaRepository;
    public List<Obavjestenja> findAll() {
        return obavjestenjaRepository.findAll();
    }

    public Optional<Obavjestenja> findById(Long id) {
        return obavjestenjaRepository.findById(id);
    }

    public Obavjestenja save(Obavjestenja obavjestenje) {
        return obavjestenjaRepository.save(obavjestenje);
    }

    public void deleteById(Long id) {
        obavjestenjaRepository.deleteById(id);
    }

    public List<Obavjestenja> findByFakultetIDTop10(Long fakultetID) {
        return obavjestenjaRepository.findTop10ByFakultetIDOrderByGodinaDescMesecDescDanDesc(fakultetID);
    }

    public List<Obavjestenja> findByFakultetIDAndMesecAndGodina(Long fakultetID,Integer mesec,Integer godina) {
        return obavjestenjaRepository.findByFakultetIDAndMesecAndGodina(fakultetID,mesec,godina);
    }
}
