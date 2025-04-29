package org.projekat.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.projekat.dtos.OsobaDTO;
import org.projekat.service.OsobaService;
import org.projekat.statics.Wayne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth/v1/osoba")
public class OsobaController {
    @Autowired
    private OsobaService osobaService;
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<OsobaDTO>>getOsoba(@PathVariable long id ){
        //todo(en):dodat provjeru tokena i id ;
        return osobaService.getOsoba(id).thenApply(ResponseEntity::ok);
    }
}
