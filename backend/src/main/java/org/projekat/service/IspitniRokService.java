package org.projekat.service;

import org.projekat.model.IspitniRok;
import org.projekat.repository.IspitniRokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class IspitniRokService {
    @Autowired
    private IspitniRokRepository ispitniRokRepository;

    @Async
    public CompletableFuture<List<IspitniRok>> getAktineRokove()throws Exception {
        return  CompletableFuture.completedFuture(ispitniRokRepository.getAktivneRokove());
    }

    @Async
    public CompletableFuture<List<IspitniRok>> findAll() {
        return CompletableFuture.completedFuture(ispitniRokRepository.findAll());
    }

    @Async
    public CompletableFuture<IspitniRok> findById(Long id) {
        return CompletableFuture.completedFuture(ispitniRokRepository.findById(id).orElseThrow(()-> new RuntimeException("error nema id")));
    }

    @Async
    public CompletableFuture<IspitniRok> save(IspitniRok ispitniRok) {
        return CompletableFuture.completedFuture(ispitniRokRepository.save(ispitniRok));
    }

    @Async
    public CompletableFuture<HttpStatus> deleteById(Long id) {
        ispitniRokRepository.deleteById(id);
        return CompletableFuture.completedFuture(HttpStatus.OK);
    }
}
