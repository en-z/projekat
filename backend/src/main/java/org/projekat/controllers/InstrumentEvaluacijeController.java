package org.projekat.controllers;

import org.projekat.model.InstrumentEvaluacije;
import org.projekat.model.StudiskiProgram;
import org.projekat.repositorys.InstrumentEvaluacijeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instrumentiEvaluacije")
public class InstrumentEvaluacijeController {
    @Autowired
    private InstrumentEvaluacijeRepository instrumentEvaluacijeRepository;
    @GetMapping
    public List<InstrumentEvaluacije> getAllInstrumenti(){
        return (List<InstrumentEvaluacije>) instrumentEvaluacijeRepository.findAll();
    }
    @GetMapping
    public List<InstrumentEvaluacije> getAllByStudiski(@RequestBody StudiskiProgram program){
        return (List<InstrumentEvaluacije>) instrumentEvaluacijeRepository.findByStudiskiProgram(program);
    }
    @GetMapping("/{id}")
    public InstrumentEvaluacije getInstrument(@PathVariable long id){
        return instrumentEvaluacijeRepository.findById(id).orElseThrow(()->new RuntimeException("InstrumentEval nije pronadjen"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentEvaluacije postInstrument(@RequestBody InstrumentEvaluacije instrumentEvaluacije){
        return instrumentEvaluacijeRepository.save(instrumentEvaluacije);
    }
    @PutMapping("/{id}")
    public InstrumentEvaluacije putInstrument(@PathVariable long id,@RequestBody InstrumentEvaluacije newIns) {
        InstrumentEvaluacije ie = instrumentEvaluacijeRepository.findById(id).orElseThrow(()->new RuntimeException("InstrumentEval nije pronadjen"));
        ie.setTip(newIns.getTip());
        ie.setOpis(newIns.getOpis());
        ie.setPredmet(newIns.getPredmet());
        return instrumentEvaluacijeRepository.save(ie);
    }
    @DeleteMapping("{id}")
    public void deletInst(@PathVariable long id){
        instrumentEvaluacijeRepository.deleteById(id);
    }
}
