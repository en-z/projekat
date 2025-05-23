package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.InstrumentEvaluacijeDTO;
import org.projekat.model.InstrumentEvaluacije;
import org.projekat.model.Nastavnik;
import org.projekat.model.Predmet;
import org.projekat.service.InstrumentEvaluacijeService;
import org.projekat.service.PredmetService;
import org.projekat.service.NastavnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evaluacije")
@RequiredArgsConstructor
public class InstrumentEvaluacijeController {

    private final InstrumentEvaluacijeService service;
    private final PredmetService predmetService;
    private final NastavnikService nastavnikService;

    @GetMapping
    public ResponseEntity<List<InstrumentEvaluacijeDTO>> getAll() {
        List<InstrumentEvaluacijeDTO> dtos = service.findAll().stream()
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
        Predmet predmet = predmetService.findById(dto.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));
        Nastavnik nastavnik = nastavnikService.findById(dto.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nastavnik ne postoji"));

        InstrumentEvaluacije instrument = new InstrumentEvaluacije();
        instrument.setTip(dto.getTip());
        instrument.setOpis(dto.getOpis());
        instrument.setPredmet(predmet);
        instrument.setNastavnik(nastavnik);

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
        return new InstrumentEvaluacijeDTO(i.getId(), i.getTip(), i.getOpis(),
                i.getPredmet().getId(), i.getNastavnik().getId());
    }
}
