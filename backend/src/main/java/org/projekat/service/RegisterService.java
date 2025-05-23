package org.projekat.service;

import org.projekat.dto.RegisterDTO;
import org.projekat.model.Adresa;
import org.projekat.model.Osoba;
import org.projekat.model.User;
import org.projekat.repository.AdresaRepository;
import org.projekat.repository.OsobaRepository;
import org.projekat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private OsobaRepository osobaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Async
    public CompletableFuture<ResponseEntity<?>> addUser(RegisterDTO regDto) {
        if (userRepository.existsByEmail(regDto.getEmail())){
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CONFLICT).body("Email postoji"));
        }
        regDto.setPassword(passwordEncoder.encode(regDto.getPassword()));
        User user = new User();
        user.setEmail(regDto.getEmail());
        user.setPassword(regDto.getPassword());
        Osoba osoba = new Osoba();
        osoba.setUser(user);
        osoba.setIme(regDto.getIme());
        osoba.setPrezime(regDto.getPrezime());
        Adresa adresa = adresaRepository.findByUlicaAndBrojAndGradAndDrzava(
                regDto.getUlica(),
                regDto.getBroj(),
                regDto.getGrad(),
                regDto.getDrzava()
        ).orElseGet(() -> {
            Adresa nadresa = new Adresa();
            nadresa.setUlica(regDto.getUlica());
            nadresa.setBroj(regDto.getBroj());
            nadresa.setGrad(regDto.getGrad());
            nadresa.setDrzava(regDto.getDrzava());
            return nadresa;
        });
        osoba.setAdresa(adresa);
        osobaRepository.save(osoba);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.OK).body("User kreiran"));
    }
}
