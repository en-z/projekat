package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.entity.KolokvijumRezultat;
import com.projekat.nastavnik_service.service.KolokvijumiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/nastavnik/kolokvijumi")
public class KolokvijumRezultatService {
    @Autowired
    private KolokvijumiService kolokvijumiService;
    @GetMapping
    public List<KolokvijumRezultat> getAll()  {
        return kolokvijumiService.getAll();
    }
    @GetMapping("/student/{id}")
    public List<KolokvijumRezultat>getAllByStudentId(@PathVariable  Long id){
        return kolokvijumiService.getAllByStudentId(id);
    }
    @GetMapping("/predmet/{id}")
    public List<KolokvijumRezultat>getAllByPredmetId(@PathVariable  Long id){
        return kolokvijumiService.getAllByPredmetId(id);
    }
    @GetMapping("/predmet/{predmetId}/student/{studentId}")
    public List<KolokvijumRezultat>getAllStudentPredmet(@PathVariable Long predmetId,@PathVariable Long studentId){
        return kolokvijumiService.getAllStudentPredmet(predmetId,studentId);
    }
    @GetMapping("/kolokvijum/{id}")
    public List<KolokvijumRezultat>getAllByKolokvijumId(@PathVariable  Long id){
        return kolokvijumiService.getAllByKolokvijumId(id);
    }
    @PostMapping
    public List<KolokvijumRezultat>saveAll(@RequestBody List<KolokvijumRezultat> kolokvijumRezultats){
        return kolokvijumiService.saveAll(kolokvijumRezultats);
    }
    @DeleteMapping("{id}")
    public void deleteById(Long id){
        kolokvijumiService.delete(id);
    }
}
