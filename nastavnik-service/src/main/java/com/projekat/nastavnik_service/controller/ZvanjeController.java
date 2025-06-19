package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.entity.Zvanje;
import com.projekat.nastavnik_service.service.ZvanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nastavnik/zvanje")
public class ZvanjeController {
   @Autowired
    ZvanjeService zvanjeService;

   @GetMapping
    public ResponseEntity<List<Zvanje>>getAll(){
       return ResponseEntity.ok(zvanjeService.getAll());
   }
   @GetMapping("/{id}")
    public ResponseEntity<Zvanje> getById(@PathVariable long id){
      return ResponseEntity.ok(zvanjeService.getById(id)) ;
   }
    @GetMapping("by-nastavnik/{id}")
    public ResponseEntity<?> getByIdn(@PathVariable long id){
        return ResponseEntity.ok(zvanjeService.findByNastavnikId(id)) ;
    }
   @PostMapping
    public Zvanje post(@RequestBody Zvanje zvanje){
       return zvanjeService.create(zvanje);
   }
   @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
       zvanjeService.delete(id);
   }
}
