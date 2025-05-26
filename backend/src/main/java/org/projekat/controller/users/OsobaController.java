package org.projekat.controller.users;

import org.projekat.dto.users.OsobaDTO;
import org.projekat.model.users.Osoba;
import org.projekat.repository.users.OsobaRepository;
import org.projekat.service.users.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/osobe")
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

    @GetMapping("/admin/{id}")
    public Osoba getOsoba(@PathVariable long id ){
        //todo(en):maknut id sa patha kad ga ima u token tu je za
        return osobaRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
    }
}
