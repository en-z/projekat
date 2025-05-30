package org.projekat.service;

import org.projekat.dto.RegisterDTO;
import org.projekat.model.Adresa;
import org.projekat.model.Osoba;
import org.projekat.model.Student;
import org.projekat.model.User;
import org.projekat.repository.AdresaRepository;
import org.projekat.repository.users.OsobaRepository;
import org.projekat.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
    @Autowired
    private StudentService studentService;
    @Async
    public CompletableFuture<ResponseEntity<?>> addUser(RegisterDTO regDto)throws Exception {
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
        Date d = new Date();
        Student s = new Student();
        s.setOsoba(osoba);
        s.setProsecnaOcena(0);
        s.setBrojIndeksa("");//Todo:dodaj idnex
        s.setOsvojeniESPB(0);
        s.setGodinaUpisa(LocalDate.now().getYear());
        s.setGodinaStudija(1);
        Student sacuvan =  studentService.saveStudent(s).get();
        sacuvan.setBrojIndeksa(genIndex(sacuvan.getGodinaUpisa(),sacuvan.getOsoba().getUser().getId()));
        studentService.saveStudent(s);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.OK).body("User kreiran"));
    }
    private String genIndex(int year,long id){
       return String.format("%d%06d",year,id);
    }
}
