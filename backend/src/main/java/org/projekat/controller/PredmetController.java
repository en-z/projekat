package org.projekat.controller;

import lombok.RequiredArgsConstructor;
import org.projekat.dto.PredmetDTO;
import org.projekat.dto.StudiskiDTO;
import org.projekat.jwt.CustomUserDetails;
import org.projekat.model.Predmet;
import org.projekat.service.PredmetService;
import org.projekat.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmeti")
@RequiredArgsConstructor
public class PredmetController {

    private final PredmetService predmetService;
    @Autowired
    private StudentService studentServiceo;
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

    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    @GetMapping("/predmeti-za-upis")
    public ResponseEntity<?> getPredmeteZaUpis() throws Exception{
        long studentId = extractUserIdFromAuth();
        return studentServiceo.getPredmeteZaUpis(studentId).get();
    }

    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    @GetMapping("/aktivni-predmeti")
    public ResponseEntity<List<PredmetDTO>> getPredmeteKojiNisuPolozeni() { // ako predmet nije polozen onda se slusa ako godina studija je semestar*2;
        long studentId = extractUserIdFromAuth();
        return ResponseEntity.ok(predmetService.getPredmetZaSlusanje(studentId));
    }

    private long extractUserIdFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}

