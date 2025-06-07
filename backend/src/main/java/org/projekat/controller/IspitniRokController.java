package org.projekat.controller;

import org.projekat.model.IspitniRok;
import org.projekat.service.IspitniRokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ispitniRok")
public class IspitniRokController {
    @Autowired
    private IspitniRokService ispitniRokService;
    @GetMapping("/aktivni")
    public List<IspitniRok> getAktivne()throws Exception{
        return ispitniRokService.getAktineRokove().get();
    }
    @GetMapping
    public List<IspitniRok>getAll()throws Exception{
        return ispitniRokService.findAll().get();
    }
    @PostMapping
    public IspitniRok postIspit(IspitniRok ispitniRok)throws Exception{
       return ispitniRokService.save(ispitniRok).get();
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteRok(@PathVariable long id)throws Exception{
        return ispitniRokService.deleteById(id).get();
    }

}
