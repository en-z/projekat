package com.projekat.biblioteka_service.service;

import com.projekat.biblioteka_service.DTO.ZahtjevSearch;
import com.projekat.biblioteka_service.DTO.ZahtjevSearchSpecifikacija;
import com.projekat.biblioteka_service.entity.Zahtjev;
import com.projekat.biblioteka_service.repository.ZahtjevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZahtjevService {
    @Autowired
    private ZahtjevRepository zahtjevRepository;
    public List<Zahtjev> search(ZahtjevSearch criteria) {
        return zahtjevRepository.findAll(ZahtjevSearchSpecifikacija.getSpecification(criteria));
    }
    public Zahtjev save(Zahtjev zahtjev) {
        return zahtjevRepository.save(zahtjev);
    }

    public void deleteById(Long id) {
        zahtjevRepository.deleteById(id);
    }
}
