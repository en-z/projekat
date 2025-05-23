package org.projekat.service;

import org.projekat.model.StudijskiProgram;
import org.projekat.repositorys.StudiskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class StudijskiProgramService {
    @Autowired
    private StudiskiProgramRepository studijskiProgramRepository;
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
}
