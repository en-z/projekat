package org.projekat.service;

import lombok.RequiredArgsConstructor;
import org.projekat.model.SlusanjePredmeta;
import org.projekat.repository.SlusanjePredmetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlusanjePredmetaService {
    private final SlusanjePredmetaRepository repository;

    public List<SlusanjePredmeta> findAll() {
        return repository.findAll();
    }

    public List<SlusanjePredmeta> findByPredmetId(Long predmetId) {
        return repository.findByPredmetId(predmetId);
    }

    public List<SlusanjePredmeta> findByStudentId(Long studentId) {
        return repository.findByStudent_Osoba_User_Id(studentId);
    }

    public SlusanjePredmeta save(SlusanjePredmeta slusanje) {
        return repository.save(slusanje);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
