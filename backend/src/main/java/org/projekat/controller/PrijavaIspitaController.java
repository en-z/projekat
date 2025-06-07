package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.PrijavaIspitaDTO;
import org.projekat.dto.PrijavaIspitaResponseDTO;
import org.projekat.jwt.CustomUserDetails;
import org.projekat.mapper.users.StudentMapper;
import org.projekat.service.PrijavaIspitaService;
import org.projekat.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prijave")
@RequiredArgsConstructor
public class PrijavaIspitaController {

    private final PrijavaIspitaService service;
    @Autowired
    private StudentService studentServiceo;
    @GetMapping("/predmet/{predmetId}")
    public ResponseEntity<List<PrijavaIspitaResponseDTO>> getPrijaveZaPredmet(@PathVariable Long predmetId) {
        List<PrijavaIspitaResponseDTO> dtos = service.findByPredmetId(predmetId).stream()
                .map(p -> new PrijavaIspitaResponseDTO(
                        p.getId(),
                        p.getRok(),
                        p.getDatumOdrzavanja(),
                        p.getDatumPrijave(),
                        p.getPredmet().getId(),
                        StudentMapper.toDTO(p.getStudent())
                ))
                .toList();

        return ResponseEntity.ok(dtos);
    }
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    @PostMapping
    public ResponseEntity<Object> prijaviIspit(@RequestBody PrijavaIspitaDTO dto)throws Exception {
        long studentId = extractUserIdFromAuth();
        return studentServiceo.prijaviIspit(dto, studentId)
                .thenApply(ResponseEntity::new).get();
    }

    private long extractUserIdFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
