package org.projekat.service;

import org.projekat.model.StudijskiProgram;
import org.projekat.repository.StudijskiProgramRepository;
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
    public CompletableFuture<StudijskiProgram> save(StudijskiProgram program) {
        return CompletableFuture.completedFuture(studijskiProgramRepository.save(program));
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
    public CompletableFuture<List<org.projekat.dtos.StudiskiDTO>> getStudiskiByFakultet(long id){
        List<org.projekat.dtos.StudiskiDTO> studiskiProgramList = studijskiProgramRepository.findByFakultet_Id(id).stream().map(f->new org.projekat.dtos.StudiskiDTO(f.getId(),f.getNaziv(),f.getOpis(),f.getRukovodilac(),f.getFakultet().getId())).collect(Collectors.toList());
        return CompletableFuture.completedFuture(studiskiProgramList);
    }
}
