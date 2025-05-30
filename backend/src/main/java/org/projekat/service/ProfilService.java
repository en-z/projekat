package org.projekat.service;

import org.projekat.dto.ProfilDTO;
import org.projekat.model.Adresa;
import org.projekat.model.Nastavnik;
import org.projekat.model.Osoba;
import org.projekat.repository.*;
import org.projekat.repository.users.NastavnikRepository;
import org.projekat.repository.users.OsobaRepository;
import org.projekat.repository.users.StudentRepository;
import org.projekat.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProfilService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NastavnikRepository nastavnikRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private OsobaRepository osobaRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Async
    public CompletableFuture<ResponseEntity<ProfilDTO>> getProfil(long id, List<String> roles){
        ProfilDTO p = new ProfilDTO();
        if(roles.contains("ROLE_NASTAVNIK")){
            Nastavnik n =nastavnikRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
            p.ntoDto(n);
        }else{
           Osoba o = osobaRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
           p.OtoDto(o);
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(p));
    }
    @Async
    public CompletableFuture<ResponseEntity<?>>editProfil(@RequestBody ProfilDTO dto, long id){
        Osoba o = osobaRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
        o.getUser().setEmail(dto.getEmail());
        o.getUser().setPassword(dto.getPassword());// encode
        Adresa a = adresaRepository.findByUlicaAndBrojAndGradAndDrzava(dto.getAdresa().getUlica(),dto.getAdresa().getBroj(),dto.getAdresa().getGrad(),dto.getAdresa().getDrzava()).orElseGet(() -> {
            Adresa newAdresa = new Adresa();
            newAdresa.setUlica(dto.getAdresa().getUlica());
            newAdresa.setBroj(dto.getAdresa().getBroj());
            newAdresa.setGrad(dto.getAdresa().getGrad());
            newAdresa.setDrzava(dto.getAdresa().getDrzava());
            return newAdresa;
        });
        o.setAdresa(a);
        o.setIme(dto.getIme());
        o.setPrezime(dto.getPrezime());
        if(dto.getStatus().isEmpty()){
            Nastavnik n = nastavnikRepository.findById(id).orElseThrow(()->new RuntimeException("no nastavnik"));
            n.setOsoba(o);
            n.setBiografija(dto.getBiografija());
            n.setStatus(dto.getStatus());
            nastavnikRepository.save(n);
        }else {
            osobaRepository.save(o);
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok("ok"));// promjenit
    }
}
