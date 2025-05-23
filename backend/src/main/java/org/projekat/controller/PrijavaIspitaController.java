package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.PrijavaIspitaResponseDTO;
import org.projekat.mapper.StudentMapper;
import org.projekat.service.PrijavaIspitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prijave")
@RequiredArgsConstructor
public class PrijavaIspitaController {

    private final PrijavaIspitaService service;

    @GetMapping("/predmet/{predmetId}")
    public ResponseEntity<List<PrijavaIspitaResponseDTO>> getPrijaveZaPredmet(@PathVariable Long predmetId) {
        List<PrijavaIspitaResponseDTO> dtos = service.findByPredmetId(predmetId).stream()
                .map(p -> new PrijavaIspitaResponseDTO(
                        p.getId(),
                        p.getGodina(),
                        p.getRok(),
                        p.getDatumPrijave(),
                        p.getPredmet().getId(),
                        StudentMapper.toDTO(p.getStudent())
                ))
                .toList();

        return ResponseEntity.ok(dtos);
    }
}
