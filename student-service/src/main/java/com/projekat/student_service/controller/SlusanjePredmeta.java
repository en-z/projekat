package com.projekat.student_service.controller;

import com.projekat.student_service.dto.SlusanjaReqDto;
import com.projekat.student_service.dto.SlusanjePredmetaDTO;
import com.projekat.student_service.dto.StudentDTO;
import com.projekat.student_service.entity.Student;
import com.projekat.student_service.repository.StudentRepository;
import com.projekat.student_service.service.SlusanjePredmetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/slusanja")
public class SlusanjePredmeta {
    @Autowired
    private SlusanjePredmetaService slusanjePredmetaService;
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/predmet/{id}")
    public ResponseEntity<List<SlusanjePredmetaDTO>> getStudenteNaPredmet(@PathVariable long id){
       return ResponseEntity.ok(slusanjePredmetaService.getStudentiNaPredmetu(id));
    }
    @GetMapping("/student")
    public ResponseEntity<?> getPredmetiStudenta(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);
        Student s = studentRepository.findByUserId(id).orElseThrow(()->new RuntimeException("error"));
        return ResponseEntity.ok(slusanjePredmetaService.getPredmetiStudenta(s.getId()));
    }
    @PostMapping
    public ResponseEntity<SlusanjePredmetaDTO> create(@RequestBody List<SlusanjaReqDto> dto){
        return ResponseEntity.ok(slusanjePredmetaService.upisiStudeenta(dto));
    }
    @DeleteMapping()
    public ResponseEntity<Void> obrisi(@RequestParam long studentId,@RequestParam long predmetId){
        slusanjePredmetaService.delete(studentId,predmetId);
        return ResponseEntity.noContent().build();
    }
}
