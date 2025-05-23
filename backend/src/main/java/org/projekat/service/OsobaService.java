package org.projekat.service;

import org.projekat.dto.OsobaDTO;
import org.projekat.model.Osoba;
import org.projekat.repository.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OsobaService {
    @Autowired
    private OsobaRepository osobaRepository;
    @Async
    public CompletableFuture<OsobaDTO> getOsoba(long id){
        System.out.println("2");
        Osoba osoba = osobaRepository.findById(id).orElseThrow(()->new RuntimeException("Osoba ne postoji"));
        System.out.println("3");
        OsobaDTO osobaDTO = new OsobaDTO();
        osobaDTO.setIme(osoba.getIme());
        osobaDTO.setPrezime(osoba.getPrezime());
        osobaDTO.setAdresa(osoba.getAdresa());
        osobaDTO.setEmail(osoba.getUser().getEmail());
        return CompletableFuture.completedFuture(osobaDTO);
    }

    public List<Osoba> findAll(){
        return osobaRepository.findAll();
    }
}
