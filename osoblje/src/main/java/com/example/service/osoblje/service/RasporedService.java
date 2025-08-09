package com.example.service.osoblje.service;

import com.example.service.osoblje.models.Raspored;
import com.example.service.osoblje.repository.RasporedRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class RasporedService {
    @Autowired
    private RasporedRepostory rasporedRepostory;
    public List<Raspored> findAll() {
        return rasporedRepostory.findAll();
    }

    public Optional<Raspored> findById(Long id) {
        return rasporedRepostory.findById(id);
    }

    public Raspored save(Raspored raspored){
        return rasporedRepostory.save(raspored);
    }
    public List<Raspored> saveAll(List<Raspored> raspored) {
        return rasporedRepostory.saveAll(raspored);
    }

    public void deleteById(Long id) {
        rasporedRepostory.deleteById(id);
    }

    public List<Raspored> findByProgramId(Long programId) {
        return rasporedRepostory.findByProgramId(programId);
    }
}
