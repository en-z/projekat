package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.client.AuthClient;
import com.projekat.nastavnik_service.dto.NastavnikCreateDTO;
import com.projekat.nastavnik_service.dto.NastavnikDTO;
import com.projekat.nastavnik_service.dto.RegisterDTO;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.repository.AngazovanjaRepository;
import com.projekat.nastavnik_service.repository.NastavnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NastavnikService {
   @Autowired
    private NastavnikRepository nastavnikRepository;
   @Autowired
    private AuthClient authClient;
   @Autowired
   private AngazovanjaRepository angazovanjaRepository;

   public NastavnikCreateDTO create(NastavnikCreateDTO dto){
       RegisterDTO n = new RegisterDTO();
       n.setEmail(dto.getEmail());
       n.setPassword(dto.getPassword());
       n.setRoles(dto.getRoles());
       n.setIme(dto.getIme());
       n.setPrezime(dto.getPrezime());
       RegisterDTO res = authClient.register(n).getBody();
       if(res ==null){
           throw new RuntimeException("puko authclient u registraciji");
       }
       Nastavnik nastavnik = new Nastavnik();
       nastavnik.setAdresa(dto.getAdresa());
       nastavnik.setBiografija(dto.getBiografija());
       nastavnik.setUserId(res.getId());
       nastavnik.setPrezime(dto.getPrezime());
       nastavnik.setIme(dto.getIme());
       nastavnikRepository.save(nastavnik);
       return dto;
   }
   public NastavnikDTO put(NastavnikDTO dto){
       Nastavnik n = nastavnikRepository.findById(dto.getId()).orElseThrow(()->new RuntimeException("error"));
       n.setIme(dto.getIme());
       n.setPrezime(dto.getPrezime());
       n.setStatus(dto.getStatus());
       n.setAdresa(dto.getAdresa());
       n.setBiografija(dto.getBiografija());
       nastavnikRepository.save(n);
       return dto;
   }
   public List<NastavnikDTO> getAll(){
       return nastavnikRepository.findAll().stream().map(n->NastavnikService.toDTO(n)).collect(Collectors.toList());
   }
   public NastavnikDTO getById(long id){
       Nastavnik n = nastavnikRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
       NastavnikDTO dto = NastavnikService.toDTO(n);
       return dto;
   }

    public NastavnikDTO getByUserId(long id){
        Nastavnik n = nastavnikRepository.findByUserId(id).orElseThrow(()->new RuntimeException("error"));
        NastavnikDTO dto = NastavnikService.toDTO(n);
        return dto;
    }
    public void delete(long id){
        Nastavnik n = nastavnikRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
       angazovanjaRepository.deleteAllInBatch(n.getAngazovanja());
       authClient.delete(n.getUserId());
       nastavnikRepository.deleteById(id);
    }

   private static NastavnikDTO toDTO(Nastavnik n){
      NastavnikDTO dto = new NastavnikDTO();
        dto.setId(n.getId());
       dto.setIme(n.getIme());
       dto.setPrezime(n.getPrezime());
       dto.setStatus(n.getStatus());
       dto.setAdresa(n.getAdresa());
       dto.setBiografija(n.getBiografija());
       return dto;
   }
}
