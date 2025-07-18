package org.projekat.service;

import org.projekat.dto.PredmetDTO;
import org.projekat.dto.UniverzitetDTO;
import org.projekat.model.*;
import org.projekat.repository.StudijskiProgramRepository;
import org.projekat.repository.users.NastavnikRepository;
import org.projekat.repositorys.FakultetRepository;
import org.projekat.repository.PredmetRepository;
import org.projekat.repository.UniverzitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UniverzitetService {
    @Autowired
    private UniverzitetRepository univerzitetRepository;
    @Autowired
    private FakultetRepository fakultetRepository;
    @Autowired
    private StudijskiProgramRepository studiskiProgramRepository;
    @Autowired
    private PredmetRepository predmetRepository;
    @Autowired
    private NastavnikRepository nastavnikRepository;
    @Async
    public CompletableFuture<List<Univerzitet>> getUniverzitete(){
        List<Univerzitet> univerzitetList = univerzitetRepository.findAll();
        return CompletableFuture.completedFuture(univerzitetList);
    }
    @Async
    public CompletableFuture<List<PredmetDTO>> getPredmetByStudiski(long id){
        List<PredmetDTO> predmetList = predmetRepository.findByStudijskiProgram_Id(id).stream().map(f->new PredmetDTO(f)).collect(Collectors.toList());
        return CompletableFuture.completedFuture(predmetList);
    }
    @Async
    public CompletableFuture<List<Univerzitet>> findAll() {
        return CompletableFuture.completedFuture(univerzitetRepository.findAll());
    }

    @Async
    public CompletableFuture<Univerzitet> findById(Long id) {
        Univerzitet univerzitet = univerzitetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Univerzitet not found"));
        return CompletableFuture.completedFuture(univerzitet);
    }

    @Async
    public CompletableFuture<Univerzitet> save(UniverzitetDTO dto) {
        Nastavnik n = nastavnikRepository.findById(dto.getRektor().getId()).orElseThrow(()->new RuntimeException("kakao"));
        Univerzitet u = new Univerzitet();
        u.setRektor(n);
        u.setAdresa(dto.getAdresa());//ako postoji da se ne pravi nova
        u.setKontakt(dto.getKontakt());
        u.setEmail(dto.getEmail());
        u.setOpis(dto.getOpis());
        u.setNaziv(dto.getNaziv());
        return CompletableFuture.completedFuture(univerzitetRepository.save(u));
    }

    @Async
    public CompletableFuture<Univerzitet> update(Long id, Univerzitet updatedUniverzitet) {
        Univerzitet univerzitet = univerzitetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Univerzitet not found"));
        univerzitet.update(updatedUniverzitet);
        Univerzitet saved = univerzitetRepository.save(univerzitet);
        return CompletableFuture.completedFuture(saved);
    }

    @Async
    public CompletableFuture<Void> deleteById(Long id) {
        univerzitetRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
}
