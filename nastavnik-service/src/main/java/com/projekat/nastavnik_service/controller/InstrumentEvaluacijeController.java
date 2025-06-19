package com.projekat.nastavnik_service.controller;

import com.projekat.nastavnik_service.dto.InstrumentEvaluacijeDTO;
import com.projekat.nastavnik_service.entity.InstrumentEvaluacije;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import com.projekat.nastavnik_service.service.InstrumentEvaluacijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/nastavnik/instrumenti")
public class InstrumentEvaluacijeController {
    @Autowired
    private InstrumentEvaluacijeService service;
    @Autowired
    private NastavnikRepository nastavnikRepository;

    @GetMapping
    public ResponseEntity<List<InstrumentEvaluacijeDTO>> getAll() {
        List<InstrumentEvaluacijeDTO> dtos = service.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/predmet/{id}")
    public ResponseEntity<List<InstrumentEvaluacijeDTO>> getByPredmetId(@PathVariable Long id) {
        List<InstrumentEvaluacijeDTO> dtos = service.findAll().stream()
                .filter(i -> i.getPredmetId() == (id))
                .map(this::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentEvaluacijeDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PostMapping
    public ResponseEntity<InstrumentEvaluacijeDTO> create(@RequestBody InstrumentEvaluacijeDTO dto) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);

        InstrumentEvaluacije instrument = new InstrumentEvaluacije();
        instrument.setTip(dto.getTip());
        instrument.setOpis(dto.getOpis());
        instrument.setPredmetId(dto.getPredmetId());
        Nastavnik n = nastavnikRepository.findByUserId(id).orElseThrow(()->new RuntimeException("error"));//iz jwt
        instrument.setNastavnik(n);

        InstrumentEvaluacije saved = service.save(instrument);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrumentEvaluacijeDTO> update(@PathVariable Long id, @RequestBody InstrumentEvaluacijeDTO dto) {
        Optional<InstrumentEvaluacije> existing = service.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        InstrumentEvaluacije instrument = existing.get();
        instrument.setTip(dto.getTip());
        instrument.setOpis(dto.getOpis());
        InstrumentEvaluacije updated = service.save(instrument);

        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    private InstrumentEvaluacijeDTO toDTO(InstrumentEvaluacije i) {
        return new InstrumentEvaluacijeDTO(i.getId(), i.getTip(), i.getOpis(), i.getDatumOdrzavanja(),
                i.getPredmetId(), i.getNastavnik().getId());
    }
}
