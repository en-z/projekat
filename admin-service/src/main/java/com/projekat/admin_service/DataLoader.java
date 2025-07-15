package com.projekat.admin_service;

import com.projekat.admin_service.entity.*;
import com.projekat.admin_service.entity.Fakultet;
import com.projekat.admin_service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UniverzitetRepository univerzitetRepository;
    @Autowired
    AdresaRepository adresaRepository;
    @Autowired
    FakultetRepository fakultetRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PredmetRepository predmetRepository;
    @Autowired
    StudijskiProgramRepository studijskiProgramRepository;

    @Override
    public void run(String... args) {
        /*
        // 1. Create and save Adresa
        Adresa adresa1 = new Adresa();
        adresa1.setUlica("Kralja Petra");
        adresa1.setBroj("10");
        adresa1.setGrad("Beograd");
        adresa1.setDrzava("Srbija");

        Adresa adresa2 = new Adresa();
        adresa2.setUlica("Nemanjina");
        adresa2.setBroj("45");
        adresa2.setGrad("Beograd");
        adresa2.setDrzava("Srbija");

        // 2. Create and save Admin with Adresa
        Admin admin = new Admin();
        admin.setUserId(1L);
        admin.setIme("Marko");
        admin.setPrezime("Markovic");
        admin.setAdresa(adresa1);
        adminRepository.save(admin);

        Univerzitet univerzitet = new Univerzitet();
        univerzitet.setNaziv("Univerzitet u Beogradu");
        univerzitet.setKontakt("+38111222333");
        univerzitet.setEmail("kontakt@bg.ac.rs");
        univerzitet.setOpis("Najveći univerzitet u Srbiji");
        univerzitet.setNastavnikId(1);
        univerzitet.setAdresa(adresa2);  // use existing saved adresa
        univerzitet = univerzitetRepository.save(univerzitet);

        Adresa fakultetAdresa = new Adresa();
        fakultetAdresa.setUlica("Studentski trrg");
        fakultetAdresa.setBroj("1");
        fakultetAdresa.setGrad("Beograd");
        fakultetAdresa.setDrzava("Srbija");

        Fakultet fakultet = new Fakultet();
        fakultet.setNaziv("Fakultet organizacionih nauka");
        fakultet.setOpis("Fakultet za organizaciju i upravljanje");
        fakultet.setKontakt("+38111222999");
        fakultet.setEmail("fakultet@fon.bg.ac.rs");
        fakultet.setUniverzitet(univerzitet);
        fakultet.setNastavnikId(1);
        fakultet.setAdresa(fakultetAdresa);
        fakultet = fakultetRepository.save(fakultet);

        // 5. Create and save StudijskiProgram with Fakultet
        StudijskiProgram studijskiProgram = new StudijskiProgram();
        studijskiProgram.setNaziv("Informacione tehnologije");
        studijskiProgram.setOpis("Program za IT struku");
        studijskiProgram.setFakultet(fakultet);
        studijskiProgram.setNastavnikId(1);
        studijskiProgram = studijskiProgramRepository.save(studijskiProgram);

        // 6. Create and save Predmet with StudijskiProgram
        Predmet predmet = new Predmet();
        predmet.setNaziv("Programiranje 1");
        predmet.setEsbp(6);
        predmet.setSemestar(1);
        predmet.setDan(3);
        predmet.setStudijskiProgram(studijskiProgram);
        predmetRepository.save(predmet);

        Predmet predmet1 = new Predmet();
        predmet1.setNaziv("Programiranje 2");
        predmet1.setEsbp(6);
        predmet1.setSemestar(1);
        predmet1.setDan(4);
        predmet1.setStudijskiProgram(studijskiProgram);
        predmetRepository.save(predmet1);
        System.out.println("Test data loaded successfully.");
         */
    }
}

