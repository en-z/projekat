package com.projekat.admin_service.service;

import com.projekat.admin_service.DTO.NastavnikDTO;
import com.projekat.admin_service.DTO.UniverzitetDto;
import com.projekat.admin_service.client.NastavnikClient;
import com.projekat.admin_service.entity.Univerzitet;
import com.projekat.admin_service.repository.AdresaRepository;
import com.projekat.admin_service.repository.UniverzitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniverzitetService {
    @Autowired
    private UniverzitetRepository univerzitetRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private NastavnikClient nastavnikClient;
    public UniverzitetDto getById(long id){
        Univerzitet u =univerzitetRepository.findById(id).orElseThrow(()->new RuntimeException("no id"));
        NastavnikDTO response = nastavnikClient.getNastavnikById(u.getNastavnikId()).getBody();
        UniverzitetDto univerzitetDto = new UniverzitetDto(u);
        univerzitetDto.setRektor(response);
        return univerzitetDto;
    }
    public UniverzitetDto create(UniverzitetDto dto){
        Univerzitet u = new Univerzitet();
        u.setEmail(dto.getEmail());
        u.setNaziv(dto.getNaziv());
        u.setAdresa(dto.getAdresa());
        u.setNastavnikId(dto.getRektor().getId());
        u.setKontakt(dto.getKontakt());
        u.setOpis(dto.getOpis());
        u.setAktivan(true);
        univerzitetRepository.save(u);
        return dto;
    }
    public List<UniverzitetDto> getAll() {
        List<Univerzitet> univerziteti = univerzitetRepository.findAll();

        return univerziteti.stream().map(u -> {
            UniverzitetDto dto = new UniverzitetDto(u);
            try {
                NastavnikDTO rektor = nastavnikClient.getNastavnikById(u.getNastavnikId()).getBody();
                dto.setRektor(rektor);
            } catch (Exception e) {
                // Handle unavailable rector service or missing data
                dto.setRektor(null); // or some default value
            }
            return dto;
        }).collect(Collectors.toList());
    }
    public List<UniverzitetDto> getAktivne() {
        List<Univerzitet> univerziteti = univerzitetRepository.findByAktivanTrue();

        return univerziteti.stream().map(u -> {
            UniverzitetDto dto = new UniverzitetDto(u);
            try {
                NastavnikDTO rektor = nastavnikClient.getNastavnikById(u.getNastavnikId()).getBody();
                dto.setRektor(rektor);
            } catch (Exception e) {
                // Handle unavailable rector service or missing data
                dto.setRektor(null); // or some default value
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public List<UniverzitetDto> getNeaktivne() {
        List<Univerzitet> univerziteti = univerzitetRepository.findByAktivanFalse();

        return univerziteti.stream().map(u -> {
            UniverzitetDto dto = new UniverzitetDto(u);
            try {
                NastavnikDTO rektor = nastavnikClient.getNastavnikById(u.getNastavnikId()).getBody();
                dto.setRektor(rektor);
            } catch (Exception e) {
                // Handle unavailable rector service or missing data
                dto.setRektor(null); // or some default value
            }
            return dto;
        }).collect(Collectors.toList());
    }
    public UniverzitetDto update(long id,UniverzitetDto dto){
        Univerzitet univerzitet = univerzitetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Univerzitet not found"));

        univerzitet.setNaziv(dto.getNaziv());
        univerzitet.setAdresa(dto.getAdresa());
        univerzitet.setNastavnikId(dto.getRektor().getId());
        univerzitet.setOpis(dto.opis);
        univerzitet.setKontakt(dto.kontakt);
        univerzitet.setEmail(dto.email);
        univerzitetRepository.save(univerzitet);

        return dto;
    }
    public void delete(long id){
        Univerzitet u = univerzitetRepository.findById(id) .orElse(null);
        if(u == null){
            throw new RuntimeException("Error id not found");
        }
        u.setAktivan(false);
        univerzitetRepository.save(u);
    }
}
