package org.projekat.controllers;

import org.projekat.model.Student;
import org.projekat.model.Univerzitet;
import org.projekat.repositorys.UniverzitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/univerziteti")
public class UniverzitetController {
    @Autowired
    private UniverzitetRepository univerzitetRepository;

    @GetMapping
    public List<Univerzitet> getAllUniverziteti(){
        return (List<Univerzitet>) univerzitetRepository.findAll();
    }
    @GetMapping("/{id}")
    public Univerzitet getUniverzitet(@PathVariable long id){
        return univerzitetRepository.findById(id).orElseThrow(()->new RuntimeException("Univerzitet nije pronadjen"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Univerzitet postUniverzitet(@RequestBody Univerzitet univerzitet){
        return  univerzitetRepository.save(univerzitet);
    }
    @PutMapping("/{id}")
    public Univerzitet putUniverzitet(@PathVariable long id,@RequestBody Univerzitet newUniverzitet) {
        Univerzitet univerzitet = univerzitetRepository.findById(id).orElseThrow(()->new RuntimeException("Univerzitet nije pronadjen"));
        univerzitet.setUlica(newUniverzitet.getUlica());
        univerzitet.setOpis(newUniverzitet.getOpis());
        univerzitet.setKontakt(newUniverzitet.getKontakt());
        return univerzitetRepository.save(univerzitet);
    }
    @DeleteMapping("/{id}")
    public void deleteUniverzitet(@PathVariable long id){
        Univerzitet univerzitet = univerzitetRepository.findById(id).orElseThrow(()->new RuntimeException("Univerzitet nije pronadjen"));
        univerzitetRepository.delete(univerzitet);
    }
}
