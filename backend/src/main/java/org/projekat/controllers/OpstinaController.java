package org.projekat.controllers;

import org.projekat.model.Opstina;
import org.projekat.repositorys.OpstinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/opstine")
public class OpstinaController {
    @Autowired
    private OpstinaRepository opstinaRepository;

    @GetMapping
    public List<Opstina> getAllOpstine(){
        return (List<Opstina>) opstinaRepository.findAll();
    }
    @GetMapping("/{id}")
    public Opstina getOpstina(@PathVariable long id){
        return opstinaRepository.findById(id).orElseThrow(()->new RuntimeException("opstina nije pronadjena"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Opstina postOpstina(@RequestBody Opstina opstina){
        return opstinaRepository.save(opstina);
    }
    @PutMapping("/{id}")
    public Opstina putOpstina(@PathVariable long id,@RequestBody Opstina newOpstina){
        Opstina opstina = opstinaRepository.findById(id).orElseThrow(()->new RuntimeException("opstina nije pronadjena"));
        opstina.setDrzava(newOpstina.getDrzava());
        opstina.setNaziv(newOpstina.getNaziv());
        opstina.setUlicaList(newOpstina.getUlicaList());//TODO(en):sta radi ovo ispod nije valjda dvije liste da pravi ako jes da pravim append za ovo
        return opstinaRepository.save(opstina);
    }
    @DeleteMapping("/{id}")
    public void deleteOpstina(@PathVariable long id){
        opstinaRepository.deleteById(id);
    }
}
