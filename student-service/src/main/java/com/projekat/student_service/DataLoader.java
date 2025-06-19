package com.projekat.student_service;

import com.projekat.student_service.entity.*;
import com.projekat.student_service.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdresaRepository adresaRepository;

    @Autowired
    private IspitniRokRepository ispitniRokRepository;
    @Autowired
    private SlusanjePredmetaRepository slusanjePredmetaRepository;

    @Override
    public void run(String... args) throws Exception {

        Adresa adresa = new Adresa();
        adresa.setUlica("Bulevar Oslobodjenja");
        adresa.setBroj("15");
        adresa.setGrad("Novi Sad");
        adresa.setDrzava("Srbija");

        Student student = new Student();
        student.setUserId(3L);
        student.setIme("Jovan");
        student.setPrezime("Jovanovic");
        student.setAdresa(adresa);
        student.setBrojIndeksa("20230001");
        student.setProsecnaOcena(9.1f);
        student.setGodinaUpisa(2023);
        student.setGodinaStudija(1);
        student.setStudiskiId(1L);
        student.setOsvojeniEsbp(30);
        studentRepository.save(student);

        IspitniRok ispitniRok = new IspitniRok();
        ispitniRok.setNaziv("Glavni ispitni rok 2025");
        ispitniRok.setPocetak(LocalDate.now());
        ispitniRok.setKraj(LocalDate.now().plusYears(10));
        ispitniRokRepository.save(ispitniRok);
        System.out.println("Done");
        SlusanjePredmeta slusanjePredmeta1 = new SlusanjePredmeta();
        slusanjePredmeta1.setPredmetId(1);
        slusanjePredmeta1.setStudent(student);
        slusanjePredmetaRepository.save(slusanjePredmeta1) ;
        SlusanjePredmeta slusanjePredmeta2 = new SlusanjePredmeta();
        slusanjePredmeta2.setPredmetId(2);
        slusanjePredmeta2.setStudent(student);
        slusanjePredmetaRepository.save(slusanjePredmeta2) ;
    }
}
