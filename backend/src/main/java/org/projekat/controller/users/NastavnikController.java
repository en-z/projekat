package org.projekat.controller.users;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.users.NastavnikDTO;
import org.projekat.dto.PredmetDTO;
import org.projekat.mapper.users.NastavnikMapper;
import org.projekat.model.Nastavnik;
import org.projekat.service.AngazovanjeService;
import org.projekat.service.users.NastavnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nastavnici")
@RequiredArgsConstructor
public class NastavnikController {

    private final NastavnikService nastavnikService;
    private final AngazovanjeService angazovanjeService;

    @GetMapping
    public ResponseEntity<List<NastavnikDTO>> getAll() {
        List<NastavnikDTO> dtos = nastavnikService.findAll()
                .stream()
                .map(NastavnikMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NastavnikDTO> getById(@PathVariable Long id) {
        return nastavnikService.findById(id)
                .map(NastavnikMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NastavnikDTO> create(@RequestBody NastavnikDTO dto) {
        Nastavnik saved = nastavnikService.save(NastavnikMapper.fromDTO(dto));
        return ResponseEntity.ok(NastavnikMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NastavnikDTO> update(@PathVariable Long id, @RequestBody NastavnikDTO dto) {
        dto.setId(id);
        Nastavnik updated = nastavnikService.save(NastavnikMapper.fromDTO(dto));
        return ResponseEntity.ok(NastavnikMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nastavnikService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/{id}/predmeti")
//    public ResponseEntity<List<Predmet>> getPredmetiZaNastavnika(@PathVariable Long id) {
//        return ResponseEntity.ok(angazovanjeService.findPredmetiByUserId(id));
//    }
    @GetMapping("/{id}/predmeti")
    public ResponseEntity<List<PredmetDTO>> getPredmetiZaNastavnika(@PathVariable Long id) {
        List<PredmetDTO> dtos = angazovanjeService.findPredmetiByUserId(id).stream()
                .map(predmet -> new PredmetDTO(predmet.getId(), predmet.getNaziv(), predmet.getEsbp()))
                .toList();

        return ResponseEntity.ok(dtos);
    }
}
