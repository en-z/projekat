package org.projekat.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.projekat.model.Drzava;
import org.projekat.repositorys.DrzavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drzave")
public class DrzavaController {
    @Autowired
    private DrzavaRepository drzavaRepository;
    @GetMapping
    public List<Drzava> getAllDrzave(){
        return (List<Drzava>) drzavaRepository.findAll();
    }
    @GetMapping("/{id}")
    public Drzava getDrzava(@PathVariable long id){
        return drzavaRepository.findById(id).orElseThrow(()->new RuntimeException("Drzava nije pronadjena"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Drzava postDrzava(@RequestBody Drzava drzava){
        return drzavaRepository.save(drzava);
    }
    @PutMapping("/{id}")
    public Drzava putDrzava(@PathVariable long id,@RequestBody Drzava newdrzava){
        Drzava drzava = drzavaRepository.findById(id).orElseThrow(()->new RuntimeException("Drzava nije pronadjena"));
        drzava.setIme(newdrzava.getIme());
        return drzavaRepository.save(drzava);
    }
    @DeleteMapping("/{id}")
    public void deleteDrzava(@PathVariable long id ){
        drzavaRepository.deleteById(id); }
}
