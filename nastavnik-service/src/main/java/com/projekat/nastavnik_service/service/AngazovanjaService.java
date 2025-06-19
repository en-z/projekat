package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.client.AdminClient;
import com.projekat.nastavnik_service.dto.AngazovanjaDTO;
import com.projekat.nastavnik_service.dto.PredmetDTO;
import com.projekat.nastavnik_service.entity.Angazovanja;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.repository.AngazovanjaRepository;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AngazovanjaService {
    @Autowired
    private AngazovanjaRepository angazovanjeRepository;
    @Autowired
    private AdminClient adminClient;
    @Autowired
    private NastavnikRepository nastavnikRepository;
    public List<AngazovanjaDTO> findAll() {
        return angazovanjeRepository.findAll().stream().map(a->new AngazovanjaDTO(a)).collect(Collectors.toList());
    }

    public List<AngazovanjaDTO> findByNastavnikId(long id) {
        return angazovanjeRepository.findAllByNastavnikId(id).stream().map(a->new AngazovanjaDTO(a)).collect(Collectors.toList());
    }

    public AngazovanjaDTO findById(Long id) {
        Angazovanja a = angazovanjeRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
        return new AngazovanjaDTO(a);
    }

    public AngazovanjaDTO save(AngazovanjaDTO angazovanje) {
        Angazovanja a = new Angazovanja();
        Nastavnik n =nastavnikRepository.findById(angazovanje.getNastavnikId()).orElseThrow(()->new RuntimeException("error"));
        a.setUloga(angazovanje.getUloga());
        a.setPredmetId(angazovanje.getPredmetId());
        a.setNastavnik(n);
        angazovanjeRepository.save(a);
        return angazovanje;
    }

    public void deleteById(Long id) {
        angazovanjeRepository.deleteById(id);
    }

    public List<PredmetDTO> findPredmetiByUserId(Long userId) {
        Nastavnik n =nastavnikRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("error"));
        List<Long> predmetIds = angazovanjeRepository.findAllByNastavnikId(n.getId())
                .stream()
                .map(ang -> ang.getPredmetId())
                .toList();
        if (predmetIds.isEmpty()) return new ArrayList<>();
        return adminClient.getPredmetiByIds(predmetIds).getBody();
    }
}
