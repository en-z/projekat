package org.projekat.controllers;

import lombok.Getter;
import org.projekat.model.Ulica;
import org.projekat.repositorys.UlicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ulice")
public class UlicaController {
    @Autowired
    private UlicaRepository ulicaRepository;

    @GetMapping
    public List<Ulica> getAllUlice(){
       return (List<Ulica>) ulicaRepository.findAll();
    }
    @GetMapping("/{id}")
    public Ulica getUlica(@PathVariable long id){
        return ulicaRepository.findById(id).orElseThrow(()->new RuntimeException("Ulica nije pronadjena"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ulica postUlica(@RequestBody Ulica ulica){
        return ulicaRepository.save(ulica);
    }
    @PutMapping("/{id}")
    public Ulica putUlica(@PathVariable long id,@RequestBody Ulica newUlica){
        Ulica ulica =ulicaRepository.findById(id).orElseThrow(()->new RuntimeException("Ulica nije pronadjena"));
        ulica.setBroj(newUlica.getBroj());
        ulica.setNaziv(newUlica.getNaziv());
        ulica.setOpstina(newUlica.getOpstina());
        return ulicaRepository.save(ulica);
    }
    @DeleteMapping("/{id}")
    public void deleteUlica(@PathVariable long id){
        ulicaRepository.deleteById(id);
    }
}
