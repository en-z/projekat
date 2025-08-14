package com.example.service.osoblje;

import com.example.service.osoblje.models.Kolokvijumi;
import com.example.service.osoblje.repository.KolokvijumiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private KolokvijumiRepository kolokvijumiRepository;
    @Override
    public void run(String... args) throws Exception {
        Kolokvijumi k1 = new Kolokvijumi();
        k1.setDatum(LocalDateTime.now());
        k1.setNaziv("K1 ");
        k1.setUcionica("a000");
        k1.setProgramId(1L);
        k1.setPredmetId(1L);
        kolokvijumiRepository.save(k1);
    }
}
