package com.projekat.admin_service.service;

import com.projekat.admin_service.DTO.FakultetDTO;
import com.projekat.admin_service.DTO.NastavnikDTO;
import com.projekat.admin_service.client.NastavnikClient;
import com.projekat.admin_service.entity.Fakultet;
import com.projekat.admin_service.entity.Univerzitet;
import com.projekat.admin_service.repository.FakultetRepository;
import com.projekat.admin_service.repository.UniverzitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class FakultetService {
   @Autowired
   private FakultetRepository fakultetRepository;
   @Autowired
   private NastavnikClient nastavnikClient;
    @Autowired
    private UniverzitetRepository univerzitetRepository;
   public FakultetDTO getById(long id){
       Fakultet f = fakultetRepository.findById(id).orElseThrow(()->new RuntimeException("no id"));
       FakultetDTO dto = new FakultetDTO();
       dto.setNaziv(f.getNaziv());
       dto.setAdresa(f.getAdresa());
       dto.setId(f.getId());
       dto.setOpis(f.getOpis());
       dto.setUniverzitetId(f.getUniverzitet().getId());
       NastavnikDTO nastanivk = nastavnikClient.getNastavnikById(f.getNastavnikId()).getBody();
       dto.setRektor(nastanivk);
       dto.setKontakt(f.getKontakt());
       dto.setEmail(f.getEmail());
       return dto;
   }
    public List<FakultetDTO> getAll() {
        List<Fakultet> fakulteti = fakultetRepository.findAll();

        return fakulteti.stream().map(f -> {
            FakultetDTO dto = new FakultetDTO();
            dto.setId(f.getId());
            dto.setOpis(f.getOpis());
            dto.setKontakt(f.getKontakt());
            dto.setEmail(f.getEmail());
            dto.setUniverzitetId(f.getUniverzitet().getId());
            dto.setAdresa(f.getAdresa());

            try {
                NastavnikDTO rektor = nastavnikClient.getNastavnikById(f.getNastavnikId()).getBody();
                dto.setRektor(rektor);
            } catch (Exception e) {
                // Fail-safe: rector service unavailable
                dto.setRektor(null);
            }

            return dto;
        }).collect(Collectors.toList());
    }
    public FakultetDTO update(long id, FakultetDTO dto) {
        Fakultet fakultet = fakultetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakultet not found"));

        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
                .orElseThrow(() -> new RuntimeException("Univerzitet not found"));

        fakultet.setNaziv(dto.getNaziv());
        fakultet.setOpis(dto.getOpis());
        fakultet.setKontakt(dto.getKontakt());
        fakultet.setEmail(dto.getEmail());
        fakultet.setUniverzitet(univerzitet);
        fakultet.setAdresa(dto.getAdresa());
        fakultet.setNastavnikId(dto.getRektor() != null ? dto.getRektor().getId() : fakultet.getNastavnikId());

        Fakultet updated = fakultetRepository.save(fakultet);

        return dto;
    }
    public void delete(long id){
       fakultetRepository.deleteById(id);
    }
    public List<FakultetDTO> getByUniverzitetId(long univerzitetId) {
        List<Fakultet> fakulteti = fakultetRepository.findByUniverzitet_Id(univerzitetId);

        return fakulteti.stream().map(f -> {
            FakultetDTO dto = new FakultetDTO();
            dto.setId(f.getId());
            dto.setNaziv(f.getNaziv());
            dto.setOpis(f.getOpis());
            dto.setKontakt(f.getKontakt());
            dto.setEmail(f.getEmail());
            dto.setAdresa(f.getAdresa());
            dto.setUniverzitetId(univerzitetId);
            try {
                dto.setRektor(nastavnikClient.getNastavnikById(f.getNastavnikId()).getBody());
            } catch (Exception e) {
                dto.setRektor(null);
            }
            return dto;
        }).collect(Collectors.toList());
    }
    public FakultetDTO create(FakultetDTO dto){
        Fakultet f = new Fakultet();
        f.setAdresa(dto.getAdresa());
        f.setEmail(dto.getEmail());
        f.setNaziv(dto.getNaziv());
        f.setOpis(dto.getOpis());
        f.setNastavnikId(dto.getRektor().getId());
        Univerzitet u = univerzitetRepository.findById(dto.getUniverzitetId()).orElseThrow(()->new RuntimeException("error"));
        f.setUniverzitet(u);
        fakultetRepository.save(f);
        return dto;
    }
}
