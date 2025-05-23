package org.projekat.controller;

import org.projekat.dto.OsobaDTO;
import org.projekat.jwt.CustomUserDetails;
import org.projekat.model.Osoba;
import org.projekat.repository.OsobaRepository;
import org.projekat.service.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/")
public class OsobaController {
    @Autowired
    private OsobaService osobaService;
    @Autowired
    private OsobaRepository osobaRepository;

    @GetMapping("/")
    public List<OsobaDTO> getAll() {
        return osobaService.findAll().stream()
                .map(a -> new OsobaDTO( a.getIme(), a.getPrezime(),a.getUser().getEmail(), a.getAdresa()))
                .collect(Collectors.toList());
    }

    @GetMapping("/admin/")
    public Osoba getOsoba(){
        //todo(en):maknut id sa patha kad ga ima u token tu je za
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        long id = userDetails.getId();
        return osobaRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
    }
}
