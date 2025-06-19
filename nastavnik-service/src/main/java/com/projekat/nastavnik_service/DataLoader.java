package com.projekat.nastavnik_service;

import com.projekat.nastavnik_service.entity.*;
import com.projekat.nastavnik_service.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Autowired
    private NastavnikRepository nastavnikRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private AngazovanjaRepository angazovanjaRepository;
    @Autowired
    private InstrumentEvaluacijeRepository instrumentEvaluacijeRepository;
    @Autowired
    private IshodIspitaRepository ishodIspitaRepository;

    @Override
    public void run(String... args) throws Exception {

        // Create and save Adresa first to avoid detached entity issues
        Adresa adresa = new Adresa();
        adresa.setUlica("Bulevar Kralja Aleksandra");
        adresa.setBroj("42");
        adresa.setGrad("Beograd");
        adresa.setDrzava("Srbija");

        // Create Nastavnik with managed Adresa
        Nastavnik nastavnik = new Nastavnik();
        nastavnik.setId(1L);
        nastavnik.setUserId(3L);
        nastavnik.setIme("Marko");
        nastavnik.setPrezime("Markovic");
        nastavnik.setStatus("redovni profesor");
        nastavnik.setBiografija("Strucnjak u oblasti informatike");
        nastavnik.setAdresa(adresa);

        // Create Angazovanja and link to Nastavnik
        Angazovanja angazovanje1 = new Angazovanja();
        angazovanje1.setUloga("predavac");
        angazovanje1.setPredmetId(2L);
        angazovanje1.setNastavnik(nastavnik);

        Angazovanja angazovanje2 = new Angazovanja();
        angazovanje2.setUloga("asistent");
        angazovanje2.setPredmetId(1L);
        angazovanje2.setNastavnik(nastavnik);

        List<Angazovanja> angazovanjaList = List.of(angazovanje1, angazovanje2);
        nastavnik.setAngazovanja(angazovanjaList);

        // Save nastavnik (cascade will save angazovanja)
        nastavnik = nastavnikRepository.save(nastavnik);

        // Create InstrumentEvaluacije linked to nastavnik
        InstrumentEvaluacije instrument = new InstrumentEvaluacije();
        instrument.setTip("pismeni ispit");
        instrument.setOpis("Pismeni deo ispita");
        instrument.setDatumOdrzavanja(LocalDateTime.now().plusDays(7));
        instrument.setPredmetId(1L);
        instrument.setNastavnik(nastavnik);
        instrument = instrumentEvaluacijeRepository.save(instrument);

        // Create IshodIspita linked to nastavnik and instrumentEvaluacije
        IshodIspita ishod = new IshodIspita();
        ishod.setId(1L);
        ishod.setBrojPokusaja(1);
        ishod.setBodovi(85.5f);
        ishod.setPredmetId(1L);
        ishod.setStudentId(1l);
        ishod.setNastavnik(nastavnik);
        ishod.setInstrumentEvaluacije(instrument);
        ishodIspitaRepository.save(ishod);
    }
}
