package com.projekat.biblioteka_service.controller;

import com.projekat.biblioteka_service.DTO.NotifikacijaDTO;
import com.projekat.biblioteka_service.repository.NotifikacijeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/biblioteka/notifikacije")
public class NotifikacijeController {
    @Autowired
    private NotifikacijeRepo notifikacijeRepo;
    @GetMapping
    public ResponseEntity<?>getAll(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);

        List<NotifikacijaDTO>n= notifikacijeRepo.findByUserId(id).stream().map(notifikacija -> new NotifikacijaDTO(notifikacija.getPoruka())).toList();
        return ResponseEntity.ok(n);
    }
    @GetMapping("/admin")
    public ResponseEntity<?> getAllAdmin(){
        List<NotifikacijaDTO>n= notifikacijeRepo.findAll().stream().map(notifikacija -> new NotifikacijaDTO(notifikacija.getPoruka())).toList();
        return ResponseEntity.ok(n);
    }
}
