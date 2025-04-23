package org.projekat.controllers;

import org.projekat.model.Fakultet;
import org.projekat.repositorys.FakultetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fakulteti")
public class FakultetController {
    @Autowired
    private FakultetRepository fakultetRepository;
    @GetMapping
    public List<Fakultet> getAllFaskultet(){
        return (List<Fakultet>)fakultetRepository.findAll();
    }
    @GetMapping("/{id}")
    public Fakultet getFakultet(@PathVariable long id){
        return fakultetRepository.findById(id).orElseThrow(()->new RuntimeException("Fakultet nije pronadjen"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fakultet postFakultet(@RequestBody Fakultet fakultet){
        return fakultetRepository.save(fakultet);
    }
    @PutMapping("{id}")
    public Fakultet putFakultet(@RequestBody Fakultet newFakultet,@PathVariable long id){
        Fakultet fakultet =fakultetRepository.findById(id).orElseThrow(()->new RuntimeException("Fakultet nije pronadjen"));
        fakultet.setNaziv(newFakultet.getNaziv());
        fakultet.setKontakt(newFakultet.getKontakt());
        fakultet.setOpis(newFakultet.getOpis());
        fakultet.setUniverzitet(newFakultet.getUniverzitet());
        return fakultetRepository.save(fakultet);
    }
    @DeleteMapping("/{id}")
    public void deleteFakultet(@PathVariable long id){
        Fakultet fakultet =fakultetRepository.findById(id).orElseThrow(()->new RuntimeException("Fakultet nije pronadjen"));
        fakultetRepository.delete(fakultet);
    }
}

