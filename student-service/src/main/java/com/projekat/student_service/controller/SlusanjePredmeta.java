package com.projekat.student_service.controller;

import com.projekat.student_service.dto.SlusanjePredmetaDTO;
import com.projekat.student_service.service.SlusanjePredmetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/slusanja")
public class SlusanjePredmeta {
    @Autowired
    private SlusanjePredmetaService slusanjePredmetaService;
    @GetMapping("/predmet/{id}")
    public ResponseEntity<List<SlusanjePredmetaDTO>> getStudenteNaPredmet(@PathVariable long id){
       return ResponseEntity.ok(slusanjePredmetaService.getStudentiNaPredmetu(id));
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getPredmetiStudenta(@PathVariable long id){
        return ResponseEntity.ok(slusanjePredmetaService.getPredmetiStudenta(id));
    }
    @PostMapping
    public ResponseEntity<SlusanjePredmetaDTO> create(@RequestBody SlusanjePredmetaDTO dto){
        return ResponseEntity.ok(slusanjePredmetaService.upisiStudeenta(dto));
    }
    @DeleteMapping()
    public ResponseEntity<Void> obrisi(@RequestParam long studentId,@RequestParam long predmetId){
        slusanjePredmetaService.delete(studentId,predmetId);
        return ResponseEntity.noContent().build();
    }
}
