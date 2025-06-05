package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.PredmetDTO;
import org.projekat.dto.StudiskiDTO;
import org.projekat.model.Predmet;
import org.projekat.service.PredmetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmeti")
@RequiredArgsConstructor
public class PredmetController {

    private final PredmetService predmetService;

    @GetMapping
    public ResponseEntity<List<PredmetDTO>> getAll() {
        List<PredmetDTO> dtos = predmetService.findAll().stream()
                .map(p -> new PredmetDTO(p.getId(), p.getNaziv(), p.getEsbp()))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Predmet> getById(@PathVariable Long id) {
        return predmetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Predmet post(@RequestBody PredmetDTO dto){
       return predmetService.save(dto);
    }
    @GetMapping("/by-program")
    public List<PredmetDTO>getByFakultet(@RequestParam("programId") long id)throws Exception{
        return predmetService.getPredmetByStudiski(id);
    }
}

