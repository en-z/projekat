package org.projekat.controllers;

import org.projekat.model.Predmet;
import org.projekat.repositorys.PredmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/predmeti")
public class PredmetController {
    @Autowired
    private PredmetRepository predmetRepository;

    @GetMapping
    public List<Predmet>getAllPredmeti(){
       return (List<Predmet>) predmetRepository.findAll();
    }
    @GetMapping("/{id}")
    public Predmet getPredmet(@PathVariable long id){
        return predmetRepository.findById(id).orElseThrow(()->new RuntimeException("Predmet nije pronadjen"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Predmet postPredmet(@RequestBody Predmet predmet){
        return predmetRepository.save(predmet);
    }
    @PutMapping("/{id}")
    public Predmet putPredmet(@PathVariable long id,@RequestBody Predmet newPredmet){
        Predmet predmet = predmetRepository.findById(id).orElseThrow(()->new RuntimeException("Predmet nije pronadjen"));
        predmet.setEsbp(newPredmet.getEsbp());
        predmet.setNaziv(newPredmet.getNaziv());
        predmet.setStudiskiProgram(newPredmet.getStudiskiProgram());
        return predmetRepository.save(predmet);
    }
    @DeleteMapping("/{id}")
    public void deletePredmet(@PathVariable long id){
        predmetRepository.deleteById(id);
    }
}
