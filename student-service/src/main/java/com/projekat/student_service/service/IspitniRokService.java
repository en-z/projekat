package com.projekat.student_service.service;

import com.projekat.student_service.entity.IspitniRok;
import com.projekat.student_service.repository.IspitniRokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IspitniRokService {
    @Autowired
    private IspitniRokRepository ispitniRokRepository;
    public IspitniRok getById(long id){
        return ispitniRokRepository.findById(id).orElseThrow(()->new RuntimeException("Id not found"));
    }
    public List<IspitniRok> getAll(){
        return ispitniRokRepository.findAll();
    }
    public List<IspitniRok> getAktivne(){
        return ispitniRokRepository.getAktivneRokove();
    }
    public IspitniRok create(IspitniRok ispitniRok){
        return ispitniRokRepository.save(ispitniRok);
    }
    public IspitniRok update(long id,IspitniRok ispitniRok){
        IspitniRok i = ispitniRokRepository.findById(id).orElseThrow(()->new RuntimeException("nema ispitnog roka"));
        i.setKraj(ispitniRok.getKraj());
        i.setPocetak(ispitniRok.getPocetak());
        i.setNaziv(ispitniRok.getNaziv());
        return ispitniRokRepository.save(i);
    }
    public void delete(long id){
        ispitniRokRepository.deleteById(id);
    }
}
