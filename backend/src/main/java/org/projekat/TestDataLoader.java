package org.projekat;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.projekat.dto.UniverzitetDTO;
import org.projekat.model.*;
import org.projekat.model.Nastavnik;
import org.projekat.model.Osoba;
import org.projekat.model.Student;
import org.projekat.model.User;
import org.projekat.repository.IshodIspitaRepository;
import org.projekat.repository.PredmetRepository;
import org.projekat.repository.StudijskiProgramRepository;
import org.projekat.repository.UniverzitetRepository;
import org.projekat.security.PasswordEncoderConf;
import org.projekat.service.*;
import org.projekat.service.users.NastavnikService;
import org.projekat.service.StudentService;
import org.projekat.service.users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {

    private final IshodIspitaRepository ishodIspitaService;
    private final UniverzitetRepository univerzitetService;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final org.projekat.repositorys.FakultetRepository fakultetRepository;
    private final IspitniRokService ispitniRokService;
    private final UserService userService;
    private final StudentService studentService;
    private final NastavnikService nastavnikService;
    private final AdresaService adresaService;
    private final PredmetRepository predmetService;
    private final SilabusService silabusService;
    private final TerminService terminService;
    private final InstrumentEvaluacijeService instrumentEvaluacijeService;
    private final ObavestenjeService obavestenjeService;
    private final AngazovanjeService angazovanjeService;
    private final PrijavaIspitaService prijavaIspitaService;
    private final SlusanjePredmetaService slusanjePredmetaService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordEncoderConf passwordEncoderConf;

    @Override
    @Transactional
    public void run(String... args) throws ExecutionException, InterruptedException {
        User userNastavnik = new User();
        userNastavnik.setEmail("nastavnik@test.com");
        userNastavnik.setPassword("test123");
        userNastavnik.setRoles(List.of("ROLE_NASTAVNIK"));
        userNastavnik = userService.save(userNastavnik);
        User admin= new User();
        admin.setEmail("marko@gmail.com");
        admin.setPassword("admin");
        admin.setRoles(List.of("ROLE_ADMIN"));
        admin = userService.save(admin);
        Adresa adresa = new Adresa();
        adresa.setUlica("Bulevar kralja Aleksandra");
        adresa.setBroj("73");
        adresa.setGrad("Beograd");
        adresa.setDrzava("Srbija");
        adresa = adresaService.save(adresa);
        Osoba adminO = new Osoba();
        adminO.setUser(admin);
        adminO.setPrezime("Fkdjsa;");
        adminO.setIme("Fkdjsa;");
        Osoba osobaNastavnik = new Osoba();
        osobaNastavnik.setUser(userNastavnik);
        osobaNastavnik.setIme("Marko");
        osobaNastavnik.setPrezime("Marković");
        osobaNastavnik.setAdresa(adresa);

        Nastavnik nastavnik = new Nastavnik();
        nastavnik.setOsoba(osobaNastavnik);
        nastavnik.setStatus("redovan");
        nastavnik = nastavnikService.save(nastavnik);

        User userStudent = new User();
        userStudent.setEmail("student@test.com");
        userStudent.setPassword("test123");
        userStudent.setRoles(List.of("ROLE_STUDENT"));
        userStudent = userService.save(userStudent);

        Osoba osobaStudent = new Osoba();
        osobaStudent.setUser(userStudent);
        osobaStudent.setIme("Ana");
        osobaStudent.setPrezime("Anić");
        osobaStudent.setAdresa(adresa);

        Student student = new Student();
        student.setOsoba(osobaStudent);
        student.setBrojIndeksa("2021/0012");
        student.setGodinaUpisa(2021);
        student.setProsecnaOcena(9.5f);
        student.setOsvojeniESPB(120);
        student = studentService.save(student);

        Predmet predmet = new Predmet();
        predmet.setNaziv("Programiranje 1");
        predmet.setEsbp(6);
        predmet = predmetService.save(predmet);

        Angazovanje angazovanje = new Angazovanje();
        angazovanje.setNastavnik(nastavnik);
        angazovanje.setPredmet(predmet);
        angazovanje.setUloga("PREDAVAC");
        angazovanjeService.save(angazovanje);

        Predmet predmet2 = new Predmet();
        predmet2.setNaziv("Baze Podataka");
        predmet2.setEsbp(7);
        predmet2 = predmetService.save(predmet2);

        Angazovanje angazovanje2 = new Angazovanje();
        angazovanje2.setNastavnik(nastavnik);
        angazovanje2.setPredmet(predmet2);
        angazovanje2.setUloga("PREDAVAC");
        angazovanjeService.save(angazovanje2);

        Predmet predmet3 = new Predmet();
        predmet3.setNaziv("Algoritmi i strukture podataka");
        predmet3.setEsbp(8);
        predmet3 = predmetService.save(predmet3);

        Angazovanje angazovanje3 = new Angazovanje();
        angazovanje3.setNastavnik(nastavnik);
        angazovanje3.setPredmet(predmet3);
        angazovanje3.setUloga("VEZBE");
        angazovanjeService.save(angazovanje3);

        Silabus silabus = new Silabus();
        silabus.setSadrzaj("Silabus za Programiranje 1");
        silabus.setPredmet(predmet);
        silabus.setAutor(nastavnik);
        silabusService.save(silabus);

        Termin termin = new Termin();
        termin.setTema("Petlje i uslovi");
        termin.setDatum(LocalDateTime.of(2025, 3, 15, 10, 0));
        termin.setPredmet(predmet);
        termin.setNastavnik(nastavnik);
        terminService.save(termin);

        InstrumentEvaluacije instrument = new InstrumentEvaluacije();
        instrument.setTip("Test");
        instrument.setOpis("Test 1 - osnove programiranja");
        instrument.setPredmet(predmet);
        instrument.setNastavnik(nastavnik);
        instrument = instrumentEvaluacijeService.save(instrument);
        IspitniRok ir = new IspitniRok();
        ir.setNaziv("januar");
        ir.setPocetak(LocalDate.now());
        ir.setKraj(LocalDate.of(2025,12,31));
        IspitniRok irs = ispitniRokService.save(ir).get();
        PrijavaIspita prijava = new PrijavaIspita();
        prijava.setStudent(student);
        prijava.setPredmet(predmet);
        prijava.setRok(ir);
        prijava.setDatumPrijave(LocalDate.now());
        prijavaIspitaService.save(prijava);

        Obavestenje obavestenje = new Obavestenje();
        obavestenje.setNaslov("Početak semestra");
        obavestenje.setTekst("Nastava počinje 1. oktobra u 8:00.");
        obavestenje.setDatum(LocalDateTime.now());
        obavestenje.setPredmet(predmet);
        obavestenje.setAutor(nastavnik);
        obavestenjeService.save(obavestenje);


        User userStudent2 = new User();
        userStudent2.setEmail("student2@test.com");
        userStudent2.setPassword(passwordEncoder.encode("test123"));
        userStudent2.setRoles(List.of("ROLE_STUDENT"));
        userStudent2 = userService.save(userStudent2);

        Osoba osobaStudent2 = new Osoba();
        osobaStudent2.setUser(userStudent2);
        osobaStudent2.setIme("Petar");
        osobaStudent2.setPrezime("Petrović");
        osobaStudent2.setAdresa(adresa);

        Student student2 = new Student();
        student2.setOsoba(osobaStudent2);
        student2.setBrojIndeksa("2021/0023");
        student2.setGodinaUpisa(2022);
        student2.setProsecnaOcena(8.7F);
        student2.setOsvojeniESPB(90);
        student2 = studentService.save(student2);
        PrijavaIspita prijava2 = new PrijavaIspita();
        prijava2.setStudent(student2);
        prijava2.setPredmet(predmet);
        prijava2.setRok(ir);
        prijava2.setDatumPrijave(LocalDate.now());
        prijavaIspitaService.save(prijava2);

        User userStudent3 = new User();
        userStudent3.setEmail("student3@test.com");
        userStudent3.setPassword("test123");
        userStudent3.setRoles(List.of("ROLE_STUDENT"));
        userStudent3 = userService.save(userStudent3);

        Osoba osobaStudent3 = new Osoba();
        osobaStudent3.setUser(userStudent3);
        osobaStudent3.setIme("Ivana");
        osobaStudent3.setPrezime("Ivanović");
        osobaStudent3.setAdresa(adresa);

        Student student3 = new Student();
        student3.setOsoba(osobaStudent3);
        student3.setBrojIndeksa("20210034");
        student3.setGodinaUpisa(2021);
        student3.setProsecnaOcena(7.9F);
        student3.setOsvojeniESPB(80);
        student3 = studentService.save(student3);


        // Dodavanje slušanja predmeta
        SlusanjePredmeta s1 = new SlusanjePredmeta();
        s1.setStudent(student);
        s1.setPredmet(predmet);
        slusanjePredmetaService.save(s1);

        SlusanjePredmeta s2 = new SlusanjePredmeta();
        s2.setStudent(student2);
        s2.setPredmet(predmet);
        slusanjePredmetaService.save(s2);

        SlusanjePredmeta s3 = new SlusanjePredmeta();
        s3.setStudent(student2);
        s3.setPredmet(predmet2);
        slusanjePredmetaService.save(s3);

        SlusanjePredmeta s4 = new SlusanjePredmeta();
        s4.setStudent(student3);
        s4.setPredmet(predmet3);
        slusanjePredmetaService.save(s4);
        Univerzitet u = new Univerzitet();
        u.setRektor(nastavnik);
        u.setKontakt("fkdsjalkfjdsa;lk");
        u.setOpis("dfajslk;sdfj;akdsj");
        u.setNaziv("fkalsdj;flksadj");
        u.setAdresa(adresa);
        u.setEmail("kdsfaj;lksdj");
        u=univerzitetService.save(u);
        Fakultet f = new Fakultet();
        f.setDekan(nastavnik);
        f.setAdresa(adresa);
        f.setUniverzitet(u);
        f.setNaziv("fsda");
        f=fakultetRepository.save(f);
        System.out.println("Ucitani test podaci.");
        StudijskiProgram studijskiProgram = new StudijskiProgram();
        studijskiProgram.setNaziv("dsfa;l");
        studijskiProgram.setOpis("fkdsaj;lkfjdsa");
        studijskiProgram.setFakultet(f);
        studijskiProgram.setRukovodilac(nastavnik);
        studijskiProgram=studijskiProgramRepository.save(studijskiProgram);
        predmet.setStudijskiProgram(studijskiProgram);
        predmetService.save(predmet);
        student.setProgram(studijskiProgram);
        student = studentService.save(student);
        Predmet p1 = new Predmet();
        p1.setNaziv("djfaklaw");
        p1.setStudijskiProgram(studijskiProgram);
        p1.setEsbp(8);
        p1.setDan(1);
        predmetService.save(p1);
        IshodIspita ishodIspita = new IshodIspita();
        ishodIspita.setStudent(student);
        ishodIspita.setBodovi(90);
        ishodIspita.setBrojPokusaja(1);
        ishodIspita.setPredmet(p1);
        ishodIspita.setDatumUnosa(LocalDateTime.now());
        ishodIspita.setNastavnik(nastavnik);
        ishodIspita.setInstrumentEvaluacije(instrument);
        ishodIspitaService.save(ishodIspita);
    }
}
