package com.projekat.biblioteka_service;

import com.projekat.biblioteka_service.entity.Knjiga;
import com.projekat.biblioteka_service.repository.KnjigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private KnjigaRepository knjigaRepository;

    @Override
    public void run(String... args) throws Exception {
        Knjiga knjiga1 = new Knjiga();
        knjiga1.setNaziv("Prokleta Avlija");
        knjiga1.setKategorija("Roman");
        knjiga1.setOpis("Klasicni roman Ive Andrica");
        knjiga1.setAutor("Ivo Andric");
        knjiga1.setKolicina(5);

        Knjiga knjiga2 = new Knjiga();
        knjiga2.setNaziv("Na Drini ćuprija");
        knjiga2.setKategorija("Istorijski roman");
        knjiga2.setOpis("Roman o mostu na Drini");
        knjiga2.setAutor("Ivo Andric");
        knjiga2.setKolicina(3);

        Knjiga knjiga3 = new Knjiga();
        knjiga3.setNaziv("Derviš i smrt");
        knjiga3.setKategorija("Roman");
        knjiga3.setOpis("Psiholoski roman");
        knjiga3.setAutor("Mesa Selimovic");
        knjiga3.setKolicina(7);

        knjigaRepository.saveAll(List.of(knjiga1, knjiga2, knjiga3));
    }
}
