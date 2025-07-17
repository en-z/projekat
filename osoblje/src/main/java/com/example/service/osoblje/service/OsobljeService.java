package com.example.service.osoblje.service;

import com.example.service.osoblje.models.Osoblje;
import com.example.service.osoblje.repository.OsobljeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OsobljeService {
    OsobljeRepository osobljeRepository;
    public List<Osoblje> findAll() {
        return osobljeRepository.findAll();
    }

    public Osoblje findById(Long id) {
        return osobljeRepository.findById(id).orElse(null);
    }


    public Osoblje save(Osoblje obavjestenje) {
        return osobljeRepository.save(obavjestenje);
    }

    public void deleteById(Long id) {
        osobljeRepository.deleteById(id);
    }

    public Osoblje findByUserId(Long userId) {
        return osobljeRepository.findByUserId(userId).orElse(null);
    }
}
