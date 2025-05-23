package org.projekat.service;

import org.projekat.dto.FakultetDTO;
import org.projekat.model.Fakultet;
import org.projekat.repositorys.FakultetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class FakultetService {
    @Autowired
    private FakultetRepository fakultetRepository;
    @Async
    public CompletableFuture<List<Fakultet>> findAll() {
        List<Fakultet> fakulteti = fakultetRepository.findAll();
        return CompletableFuture.completedFuture(fakulteti);
    }

    @Async
    public CompletableFuture<Fakultet> findById(Long id) {
        Fakultet fakultet = fakultetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakultet not found"));
        return CompletableFuture.completedFuture(fakultet);
    }
    @Async
    public CompletableFuture<Void> deleteById(Long id) {
        fakultetRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Fakultet> update(Long id, Fakultet updatedFakultet) {
        Fakultet fakultet = fakultetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakultet not found"));
        fakultet.update(updatedFakultet);
        fakultetRepository.save(fakultet);
        return CompletableFuture.completedFuture(fakultet);
    }
    @Async
    public CompletableFuture<Fakultet> save(Fakultet fakultet) {
        Fakultet saved = fakultetRepository.save(fakultet);
        return CompletableFuture.completedFuture(saved);
    }
}
