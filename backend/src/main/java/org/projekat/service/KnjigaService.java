package org.projekat.service;

import org.projekat.model.Knjiga;
import org.projekat.repository.KnjigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepository knjigaRepository;

    public List<Knjiga> getAll(){
       return knjigaRepository.findAll();
    }
    public Knjiga getById(long id){
        return knjigaRepository.findById(id).orElseThrow(()->new RuntimeException("Nema knjige"));
    }
    public Knjiga save(Knjiga knjiga){
        return knjigaRepository.save(knjiga);
    }
    public List<Knjiga> getByKategorija(String kategorija){
        return knjigaRepository.findByKategorijaIgnoreCase(kategorija);
    }

    public List<Knjiga> search(String naziv,String kategorija,String opis,String autor){
        return knjigaRepository.search(naziv,kategorija,opis,autor);
    }
}
