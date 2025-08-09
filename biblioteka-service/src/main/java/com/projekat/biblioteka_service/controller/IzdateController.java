package com.projekat.biblioteka_service.controller;

import com.projekat.biblioteka_service.DTO.DatumDTO;
import com.projekat.biblioteka_service.DTO.IzdajDto;
import com.projekat.biblioteka_service.entity.Izdate;
import com.projekat.biblioteka_service.entity.Knjiga;
import com.projekat.biblioteka_service.service.IzdavanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/biblioteka/izdate")
public class IzdateController {
    @Autowired
    private IzdavanjeService izdavanjeService;
    @GetMapping
    public List<Izdate> getAll(){
       return izdavanjeService.getAll();
    }
    @GetMapping("/user")
    public List<Izdate> getIzdate() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);
        return izdavanjeService.getAllUser(id);
    }

    @PostMapping("/izdaj/{id}")
    public ResponseEntity<?> izdaj(@PathVariable Long id, @RequestBody IzdajDto dto) {
        try {
            return ResponseEntity.ok(izdavanjeService.izdajKjigu(id,dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody DatumDTO dto){
        return ResponseEntity.ok(izdavanjeService.put(id,dto.datumVracanja));
    }
    @PostMapping("/vrati/{id}")
    public ResponseEntity<?> vrati(@PathVariable long id) {
        try {
            izdavanjeService.vratiKnjigu(id);
            return null;
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
