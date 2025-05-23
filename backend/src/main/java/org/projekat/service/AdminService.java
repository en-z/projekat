package org.projekat.service;

import org.hibernate.annotations.Array;
import org.projekat.dto.OsobaDTO;
import org.projekat.dto.RegisterDTO;
import org.projekat.model.*;
import org.projekat.repository.*;
import org.projekat.repositorys.*;
import org.projekat.security.PasswordEncoderConf;
import org.projekat.security.SecurityConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OsobaRepository osobaRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private NastavnikRepository nastavnikRepository;
    @Autowired
    private PasswordEncoderConf passwordEncoderConf;
    @Autowired
    private StudiskiProgramRepository studiskiProgramRepository;
    @Autowired
    private UniverzitetRepository univerzitetRepository;
    @Async
    public CompletableFuture<HttpStatus>resetSifru(long id ,User user){
        User nuser = userRepository.findById(id).orElseThrow(()->new RuntimeException("Nema korisnika sa tim id"));
        nuser = user;
        userRepository.save(nuser);
        return CompletableFuture.completedFuture(HttpStatus.OK);
    }

    @Async
    public CompletableFuture<HttpStatus>dodajAdmina(Osoba osoba){
        osoba.getUser().setPassword(passwordEncoderConf.passwordEncoder().encode(osoba.getUser().getPassword()));
        Adresa adresa = adresaRepository.findByUlicaAndBrojAndGradAndDrzava(
                osoba.getAdresa().getUlica(),
                osoba.getAdresa().getBroj(),
                osoba.getAdresa().getGrad(),
                osoba.getAdresa().getDrzava()
        ).orElseGet(() -> { return osoba.getAdresa();});
        osobaRepository.save(osoba);
        return CompletableFuture.completedFuture(HttpStatus.CREATED);
    }
    @Async
    public CompletableFuture<HttpStatus>dodajNastavnika(Nastavnik osoba){
        osoba.getOsoba().getUser().setPassword(passwordEncoderConf.passwordEncoder().encode(osoba.getOsoba().getUser().getPassword()));
        Adresa adresa = adresaRepository.findByUlicaAndBrojAndGradAndDrzava(
                osoba.getOsoba().getAdresa().getUlica(),
                osoba.getOsoba().getAdresa().getBroj(),
                osoba.getOsoba().getAdresa().getGrad(),
                osoba.getOsoba().getAdresa().getDrzava()
        ).orElseGet(() -> { return osoba.getOsoba().getAdresa();});
        nastavnikRepository.save(osoba);
        return CompletableFuture.completedFuture(HttpStatus.CREATED);
    }
    @Async
    public CompletableFuture<ResponseEntity<User>> grabByEmail(String email){
        System.out.println("in async call, auth: " + SecurityContextHolder.getContext().getAuthentication());
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            user.setPassword("");
            return CompletableFuture.completedFuture(ResponseEntity.ok(user));
        } catch (RuntimeException ex) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }
    @Async("taskExecutor")
    public CompletableFuture<User> getUserByIdAsync(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword("");
        return CompletableFuture.completedFuture(user);
    }
}
