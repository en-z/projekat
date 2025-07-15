package com.example.service.osoblje.controller;

import com.example.service.osoblje.dto.ObavjsetenjaReq;
import com.example.service.osoblje.models.Obavjestenja;
import com.example.service.osoblje.service.ObavjestenjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/osoblje/obavjestenja")
public class ObavjestenjaController {
    @Autowired
    private ObavjestenjaService obavjestenjaService;

    @GetMapping("/fakultet/{fakultetId}")
    public List<Obavjestenja> getByFakultetMesecGodina(
            @PathVariable Long fakultetId,
            @RequestParam(required = false) Integer godina,
            @RequestParam(required = false) Integer mesec
    ) {
        return obavjestenjaService.findByFakultetIDAndMesecAndGodina(fakultetId, mesec, godina);
    }
    @GetMapping("/{id}")
    public List<Obavjestenja> find(@PathVariable long id) {
        return obavjestenjaService.findByFakultetIDTop10(id);
    }

    @PostMapping
    public Obavjestenja create(@RequestBody ObavjsetenjaReq dto){
        Obavjestenja o = new Obavjestenja();
        System.out.println(dto.getNaslov());
        o.setGodina(LocalDate.now().getYear());
        o.setMesec(LocalDate.now().getMonthValue());
        o.setNaslov(dto.getNaslov());
        o.setText(dto.getText());
        o.setDan(LocalDate.now().getDayOfMonth());
        o.setFakultetID(dto.getFakultetID());
        return obavjestenjaService.save(o);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        obavjestenjaService.deleteById(id);
    }

}
