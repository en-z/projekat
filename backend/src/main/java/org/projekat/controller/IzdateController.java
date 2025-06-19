package org.projekat.controller;

import org.projekat.jwt.CustomUserDetails;
import org.projekat.model.Izdate;
import org.projekat.service.IzdavanjeService;
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

    @GetMapping("/osoba")
    public List<Izdate> getIzdate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        long id = userDetails.getId();
        return izdavanjeService.getAll(id);
    }
    @PostMapping("/izdaj/{kid}")
    public ResponseEntity<?> izdaj(@PathVariable long kid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        long oid = userDetails.getId();
        try{
            return ResponseEntity.ok(izdavanjeService.izdajKjigu(kid,oid));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/vrati/{id}")
    public ResponseEntity<?> vrati(@PathVariable long id){
        try {
            return ResponseEntity.ok(izdavanjeService.vratiKnjigu(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
