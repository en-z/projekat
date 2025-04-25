package org.projekat.controllers;

import org.projekat.model.IspitPrijava;
import org.projekat.repositorys.IspitPrijavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ispitiPrijava")
public class IspitPrijavaController {
    @Autowired
    private IspitPrijavaRepository ispitPrijavaRepository;
    @GetMapping
    public List<IspitPrijava> getAllIspitPrijava(){
       return ispitPrijavaRepository.findAll();
    }
    @GetMapping("/{id}/predmeti")
    public List<IspitPrijava> getByStudentId(@PathVariable long id){
        return (List<IspitPrijava>) ispitPrijavaRepository.findByStudentId(id);
    }
    @GetMapping("/{id}/studenti")
    public List<IspitPrijava> getByPredmetId(@PathVariable long id){
        return (List<IspitPrijava>) ispitPrijavaRepository.findByPredmetId(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IspitPrijava postIspitPrijava(@RequestBody IspitPrijava ispitPrijava){
        return ispitPrijavaRepository.save(ispitPrijava);
    }
}
