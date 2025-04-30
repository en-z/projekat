package org.projekat.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.projekat.dtos.OsobaDTO;
import org.projekat.model.Osoba;
import org.projekat.repositorys.OsobaRepository;
import org.projekat.service.OsobaService;
import org.projekat.statics.Wayne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth/v1/")
public class OsobaController {
    @Autowired
    private OsobaService osobaService;
    @Autowired
    private OsobaRepository osobaRepository;
    @GetMapping("/admin/{id}")
    public Osoba getOsoba(@PathVariable long id ){
        //todo(en):maknut id sa patha kad ga ima u token tu je za
        return osobaRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
    }
}
