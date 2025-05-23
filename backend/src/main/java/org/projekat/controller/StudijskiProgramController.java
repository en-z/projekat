package org.projekat.controller;

import org.projekat.model.StudijskiProgram;
import org.projekat.service.StudijskiProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/program")
public class StudijskiProgramController {
    @Autowired
    private StudijskiProgramService studijskiProgramService;

    @GetMapping
    public ResponseEntity<List<StudijskiProgram>> getAll()throws Exception {
        return studijskiProgramService.findAll()
                .thenApply(ResponseEntity::ok).get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudijskiProgram> getById(@PathVariable Long id)throws Exception {
        return studijskiProgramService.findById(id)
                .thenApply(ResponseEntity::ok).get();
    }

    @PostMapping
    public ResponseEntity<StudijskiProgram> create(@RequestBody StudijskiProgram program)throws Exception {
        return studijskiProgramService.save(program)
                .thenApply(saved -> new ResponseEntity<>(saved, HttpStatus.CREATED)).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudijskiProgram> update(
            @PathVariable Long id,
            @RequestBody StudijskiProgram program
    ) throws Exception{
        return studijskiProgramService.update(id, program)
                .thenApply(ResponseEntity::ok).get();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id)throws Exception {
        return studijskiProgramService.deleteById(id)
                .thenApply(v -> ResponseEntity.noContent().build()).get();
    }
}
