package org.projekat.service;

import org.projekat.dto.FakultetDTO;
import org.projekat.dto.PredmetDTO;
import org.projekat.dtos.StudiskiDTO;
import org.projekat.model.*;
import org.projekat.repositorys.FakultetRepository;
import org.projekat.repository.PredmetRepository;
import org.projekat.repositorys.StudiskiProgramRepository;
import org.projekat.repository.UniverzitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private StudiskiProgramRepository studiskiProgramRepository;
    @Autowired
    private PredmetRepository predmetRepository;
    @Async
    public CompletableFuture<List<Univerzitet>> getUniverzitete(){
        List<Univerzitet> univerzitetList = univerzitetRepository.findAll();
        return CompletableFuture.completedFuture(univerzitetList);
    }
    @Async
    public CompletableFuture<List<FakultetDTO>> getFakultetByUniverzitet(long id){
        List<FakultetDTO> fakultetList = fakultetRepository.findByUniverzitet_Id(id).stream().map(f->new FakultetDTO(f.getId(),f.getNaziv(),f.getOpis(),f.getKontakt(),f.getAdresa(),f.getDekan(),f.getUniverzitet().getId())).collect(Collectors.toList());
        return CompletableFuture.completedFuture(fakultetList);
    }
    @Async
    public CompletableFuture<List<StudiskiDTO>> getStudiskiByFakultet(long id){
        List<StudiskiDTO> studiskiProgramList = studiskiProgramRepository.findByFakultet_Id(id).stream().map(f->new StudiskiDTO(f.getId(),f.getNaziv(),f.getOpis(),f.getRukovodilac(),f.getFakultet().getId())).collect(Collectors.toList());
        return CompletableFuture.completedFuture(studiskiProgramList);
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
    public CompletableFuture<Univerzitet> save(Univerzitet univerzitet) {
        return CompletableFuture.completedFuture(univerzitetRepository.save(univerzitet));
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
