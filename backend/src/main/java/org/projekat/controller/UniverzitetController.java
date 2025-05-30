package org.projekat.controller;

import org.projekat.dto.FakultetDTO;
import org.projekat.dto.FakultetDTO;
import org.projekat.dto.PredmetDTO;
import org.projekat.dtos.StudiskiDTO;
import org.projekat.model.Fakultet;
import org.projekat.model.Predmet;
import org.projekat.model.StudijskiProgram;
import org.projekat.model.Univerzitet;
import org.projekat.service.UniverzitetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/univerzitet")
public class UniverzitetController {
    @Autowired
    private UniverzitetService univerzitetService;
    @GetMapping
    public List<Univerzitet>getUniverzitete()throws Exception{
       return univerzitetService.getUniverzitete().get();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Univerzitet> getById(@PathVariable Long id)throws Exception {
        return univerzitetService.findById(id)
                .thenApply(ResponseEntity::ok).get();
    }
    @PostMapping
    public ResponseEntity<Univerzitet> create(@RequestBody Univerzitet univerzitet)throws Exception {
        return univerzitetService.save(univerzitet)
                .thenApply(saved -> new ResponseEntity<>(saved, HttpStatus.CREATED)).get();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Univerzitet> update(@PathVariable Long id, @RequestBody Univerzitet univerzitet)throws Exception {
        return univerzitetService.update(id, univerzitet)
                .thenApply(ResponseEntity::ok).get();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws Exception{
        return univerzitetService.deleteById(id)
                .thenApply(v -> ResponseEntity.noContent().build()).get();
    }
}
