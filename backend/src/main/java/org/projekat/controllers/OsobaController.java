package org.projekat.controllers;

import org.projekat.model.Osoba;
import org.projekat.repositorys.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/osobe")
@PreAuthorize("hasAnyAuthority('ADMIN','NASTAVNIK','STUDENT')")
public class OsobaController {
    @Autowired
    private OsobaRepository osobaRepository;
    @GetMapping
    public List<Osoba> getAllOsobe(){
        return (List<Osoba>) osobaRepository.findAll();
    }
    @GetMapping("/{id}")
    public Osoba getOsoba(@PathVariable long id){
        return osobaRepository.findById(id).orElseThrow(()->new RuntimeException("Osoba nije pronadjena"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Osoba postOsoba(@RequestBody Osoba osoba){
        return osobaRepository.save(osoba);
    }
    @PutMapping("/{id}")
    public Osoba putOsoba(@PathVariable long id,@RequestBody Osoba newOsoba){
        Osoba osoba = osobaRepository.findById(id).orElseThrow(()->new RuntimeException("Osoba nije pronadjena"));
        osoba.setIme(newOsoba.getIme());
        osoba.setPrezime(newOsoba.getPrezime());
        osoba.setUlica(newOsoba.getUlica());
        return osobaRepository.save(osoba);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteOsoba(@PathVariable long id){
        osobaRepository.deleteById(id);
    }
}
