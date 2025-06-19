package com.projekat.biblioteka_service.controller;

import com.projekat.biblioteka_service.entity.Izdate;
import com.projekat.biblioteka_service.service.IzdavanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/biblioteka/izdate")
public class IzdateController {
    @Autowired
    private IzdavanjeService izdavanjeService;

    @GetMapping("/user")
    public List<Izdate> getIzdate() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);

        return izdavanjeService.getAll(id);
    }

    @PostMapping("/izdaj/{kid}")
    public ResponseEntity<?> izdaj(@PathVariable long kid) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);
        try {
            return ResponseEntity.ok(izdavanjeService.izdajKjigu(kid, id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e);
        }
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
