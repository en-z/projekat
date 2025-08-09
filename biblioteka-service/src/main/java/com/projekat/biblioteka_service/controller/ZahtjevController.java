package com.projekat.biblioteka_service.controller;

import com.projekat.biblioteka_service.DTO.ZahtjevDTO;
import com.projekat.biblioteka_service.DTO.ZahtjevSearch;
import com.projekat.biblioteka_service.DTO.ZahtjevSearchSpecifikacija;
import com.projekat.biblioteka_service.client.AuthClient;
import com.projekat.biblioteka_service.client.ImeDTO;
import com.projekat.biblioteka_service.entity.Zahtjev;
import com.projekat.biblioteka_service.service.KnjigaService;
import com.projekat.biblioteka_service.service.ZahtjevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/biblioteka/zahtjevi")
public class ZahtjevController {
    @Autowired
    private ZahtjevService zahtjevService;
    @Autowired
    private AuthClient authClient;
    @Autowired
    private KnjigaService knjigaService;
    @PostMapping("/search")
    public List<Zahtjev> search(@RequestBody ZahtjevSearch criteria){
        return zahtjevService.search(criteria);
    }
    @PostMapping
    public Zahtjev createOrUpdate(@RequestBody Long knjigaId) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = Long.parseLong(userId);
        Zahtjev zahtjev = new Zahtjev();
        zahtjev.setUserId(id);
        ImeDTO ime = authClient.getIme(id).getBody();
        zahtjev.setIme(ime.getIme());
        zahtjev.setPrezime(ime.getPrezime());
        zahtjev.setKnjiga(knjigaService.getById(knjigaId));
        return zahtjevService.save(zahtjev);
    }

    // DELETE po id-u
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        zahtjevService.deleteById(id);
    }
}
