package org.projekat.service;

import org.projekat.dto.StudiskiDTO;
import org.projekat.model.Fakultet;
import org.projekat.model.Nastavnik;
import org.projekat.model.StudijskiProgram;
import org.projekat.repository.StudijskiProgramRepository;
import org.projekat.repository.users.NastavnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class StudijskiProgramService {
    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;
    @Autowired
    private NastavnikRepository nastavnikRepository;
    @Autowired
    private org.projekat.repositorys.FakultetRepository fakultetRepository;
    @Async
    public CompletableFuture<List<StudijskiProgram>> findAll() {
        return CompletableFuture.completedFuture(studijskiProgramRepository.findAll());
    }

    @Async
    public CompletableFuture<StudijskiProgram> findById(Long id) {
        StudijskiProgram program = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));
        return CompletableFuture.completedFuture(program);
    }

    @Async
    public CompletableFuture<StudijskiProgram> save(StudiskiDTO program) {
        StudijskiProgram sp = new StudijskiProgram();
        Nastavnik n = nastavnikRepository.findById(program.getRukovodioc().getId()).orElseThrow(()->new RuntimeException("error na sacuvaj program"));
        sp.setRukovodilac(n);
        sp.setOpis(program.getOpis());
        sp.setNaziv(program.getNaziv());
        Fakultet f = fakultetRepository.findById(program.getFakultetId()).orElseThrow(()->new RuntimeException("id nije dobar"));
        sp.setFakultet(f);
        return CompletableFuture.completedFuture(studijskiProgramRepository.save(sp));
    }

    @Async
    public CompletableFuture<StudijskiProgram> update(Long id, StudijskiProgram updatedProgram) {
        StudijskiProgram program = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        program.update(updatedProgram);
        StudijskiProgram saved = studijskiProgramRepository.save(program);
        return CompletableFuture.completedFuture(saved);
    }

    @Async
    public CompletableFuture<Void> deleteById(Long id) {
        studijskiProgramRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
    @Async
    public CompletableFuture<List<org.projekat.dto.StudiskiDTO>> getStudiskiByFakultet(long id){
        List<org.projekat.dto.StudiskiDTO> studiskiProgramList = studijskiProgramRepository.findByFakultet_Id(id).stream().map(f->new StudiskiDTO(f)).collect(Collectors.toList());
        return CompletableFuture.completedFuture(studiskiProgramList);
    }
}
