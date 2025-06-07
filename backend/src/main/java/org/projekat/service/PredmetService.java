package org.projekat.service;

import org.projekat.dto.PredmetDTO;
import org.projekat.model.Predmet;
import org.projekat.model.Student;
import org.projekat.model.StudijskiProgram;
import org.projekat.repository.PredmetRepository;
import org.projekat.repository.StudijskiProgramRepository;
import org.projekat.repository.users.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Service
public class PredmetService {
    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;
    @Autowired
    private StudentRepository studentRepository;
    private final PredmetRepository predmetRepository;

    public PredmetService(PredmetRepository predmetRepository) {
        this.predmetRepository = predmetRepository;
    }

    public List<Predmet> findAll() {
        return predmetRepository.findAll();
    }

    public Optional<Predmet> findById(Long id) {
        return predmetRepository.findById(id);
    }

    public Predmet save(PredmetDTO dto) {
        Predmet predmet = dto.toPredmet();
        StudijskiProgram sp = studijskiProgramRepository.findById(dto.getStudiskiId()).orElseThrow(()->new RuntimeException("error"));
        predmet.setStudijskiProgram(sp);
        return predmetRepository.save(predmet);
    }

    public void deleteById(Long id) {
        predmetRepository.deleteById(id);
    }

    public List<Predmet> findByNaziv(String naziv) {
        return predmetRepository.findByNazivContainingIgnoreCase(naziv);
    }

    public List<PredmetDTO> getPredmetByStudiski(long id) {
        return  predmetRepository.findByStudijskiProgram_Id(id).stream().map(p->new PredmetDTO(p)).toList();
    }
    public List<PredmetDTO> getPredmetZaSlusanje(long userid){
        Student s = studentRepository.findById(userid).orElseThrow(()->new RuntimeException("erro"));
        int maxSemestar = s.getGodinaStudija()*2;
        return predmetRepository.findByNijePolozen(userid,s.getProgram().getId(),maxSemestar).stream().map(p->new PredmetDTO(p)).toList();
    }
}
