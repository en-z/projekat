package org.projekat.controllers;

import org.projekat.model.StudiskiProgram;
import org.projekat.repositorys.StudiskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/studiskiProgrami")
public class StudiskiProgramController {
    @Autowired
    private StudiskiProgramRepository studiskiProgramRepository;

    @GetMapping
    public List<StudiskiProgram> getAllStudiski(){
        return (List<StudiskiProgram>) studiskiProgramRepository.findAll();
    }
    @GetMapping("/{id}")
    public StudiskiProgram getStudiski(@PathVariable long id){
        return studiskiProgramRepository.findById(id).orElseThrow(()->new RuntimeException("Program nije pronadjen"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudiskiProgram postStudiski(@RequestBody StudiskiProgram studiskiProgram){
        return studiskiProgramRepository.save(studiskiProgram);
    }
    @PutMapping("/{id}")
    public StudiskiProgram putStudiski(@PathVariable long id, @RequestBody StudiskiProgram newStudiskiProgram){
        StudiskiProgram studiskiProgram = studiskiProgramRepository.findById(id).orElseThrow(()->new RuntimeException("Program nije pronadjen"));
        studiskiProgram.setNaziv(newStudiskiProgram.getNaziv());
        studiskiProgram.setOpis(newStudiskiProgram.getOpis());
        studiskiProgram.setFakultet(newStudiskiProgram.getFakultet());
        return studiskiProgramRepository.save(studiskiProgram);
    }
    @DeleteMapping("/{id}")
    public void deleteProgram(@PathVariable long id){
        studiskiProgramRepository.deleteById(id);
    }
}
