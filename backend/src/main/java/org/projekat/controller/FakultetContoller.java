package org.projekat.controller;

import org.projekat.dto.FakultetDTO;
import org.projekat.model.Fakultet;
import org.projekat.service.FakultetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/fakultet")
public class FakultetContoller {
    @Autowired
    private FakultetService fakultetService;

    @GetMapping
    public ResponseEntity<List<Fakultet>> getAll() throws Exception{
        return fakultetService.findAll()
                .thenApply(ResponseEntity::ok).get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fakultet> getById(@PathVariable Long id) throws Exception{
        return fakultetService.findById(id)
                .thenApply(ResponseEntity::ok).get();
    }

    @PostMapping
    public ResponseEntity<Fakultet> create(@RequestBody Fakultet fakultet) throws Exception{
        return fakultetService.save(fakultet)
                .thenApply(saved -> new ResponseEntity<>(saved, HttpStatus.CREATED)).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fakultet> update(@PathVariable Long id, @RequestBody Fakultet fakultet)throws Exception {
        return fakultetService.update(id, fakultet)
                .thenApply(ResponseEntity::ok).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id)throws Exception {
        return fakultetService.deleteById(id)
                .thenApply(v -> ResponseEntity.noContent().build()).get();
    }
}
