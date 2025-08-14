package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.entity.KolokvijumRezultat;
import com.projekat.nastavnik_service.repository.KolokvijumiRezultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KolokvijumiService {
    @Autowired
    private KolokvijumiRezultatRepository kolokvijumiRezultatRepository;

    public List<KolokvijumRezultat> getAll(){
        return kolokvijumiRezultatRepository.findAll();
    }
    public List<KolokvijumRezultat> getAllByPredmetId(Long predmetId){
        return kolokvijumiRezultatRepository.findAllByPredmetId(predmetId);
    }
    public List<KolokvijumRezultat> getAllByKolokvijumId(Long id){
        return kolokvijumiRezultatRepository.findAllByKolokvijumId(id);
    }
    public List<KolokvijumRezultat> getAllByStudentId(Long id){
        return kolokvijumiRezultatRepository.findAllByStudentId(id);
    }
    public List<KolokvijumRezultat> getAllStudentPredmet(Long predmetId,Long studentId){
        System.out.println(kolokvijumiRezultatRepository.findAllByPredmetIdAndStudentId(predmetId,studentId));
        return kolokvijumiRezultatRepository.findAllByPredmetIdAndStudentId(predmetId,studentId);
    }
    public List<KolokvijumRezultat> saveAll(List<KolokvijumRezultat> list){
       return kolokvijumiRezultatRepository.saveAll(list);
    }
    public void delete(Long id){
        kolokvijumiRezultatRepository.deleteById(id);
    }
}
