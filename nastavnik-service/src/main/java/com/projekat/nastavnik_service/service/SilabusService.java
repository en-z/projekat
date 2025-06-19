package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.entity.Silabus;
import com.projekat.nastavnik_service.repository.SilabusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SilabusService {
    @Autowired
    private SilabusRepository silabusRepository;
    public Silabus create( Silabus dto){
        return silabusRepository.save(dto);
    }
    public Optional<Silabus> findById(Long id) {
        return silabusRepository.findById(id);
    }

    public List<Silabus> findAllByPredmetId(Long predmetId) {
        return silabusRepository.findAllByPredmetId(predmetId);
    }

    public Silabus findByPredmetId(long predmetId) {
        return silabusRepository.findByPredmetId(predmetId).orElseThrow(()->new RuntimeException("error: "+predmetId));
    }
    public Silabus save(Silabus silabus) {
        return silabusRepository.save(silabus);
    }
    public Silabus update(Long silabusId, String noviSadrzaj) {
        Silabus silabus = silabusRepository.findById(silabusId)
                .orElseThrow(() -> new RuntimeException("Silabus ne postoji"));

        silabus.setSadrzaj(noviSadrzaj);
        return silabusRepository.save(silabus);
    }
    public void deleteById(long id){
        silabusRepository.deleteById(id);
    }

}
