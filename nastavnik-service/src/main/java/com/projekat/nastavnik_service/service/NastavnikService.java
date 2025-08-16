package com.projekat.nastavnik_service.service;

import com.projekat.nastavnik_service.client.AuthClient;
import com.projekat.nastavnik_service.dto.NastavnikCreateDTO;
import com.projekat.nastavnik_service.dto.NastavnikDTO;
import com.projekat.nastavnik_service.dto.RegisterDTO;
import com.projekat.nastavnik_service.entity.Adresa;
import com.projekat.nastavnik_service.entity.Nastavnik;
import com.projekat.nastavnik_service.repository.AdresaRepository;
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
   @Autowired
    private  AdresaRepository adresaRepository;

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
       Adresa adresa = adresaRepository.findByUlicaAndBrojAndGradAndDrzava(dto.getAdresa().getUlica(), dto.getAdresa().getBroj(),dto.getAdresa().getGrad(),dto.getAdresa().getDrzava()).orElse(null);
       if (adresa ==null){
           adresa = new Adresa();
           adresa.setBroj(dto.getAdresa().getBroj());
           adresa.setGrad(dto.getAdresa().getGrad());
           adresa.setDrzava(dto.getAdresa().getDrzava());
           adresa.setUlica(dto.getAdresa().getUlica());
       }
       nastavnik.setAdresa(adresa);
       nastavnik.setBiografija(dto.getBiografija());
       nastavnik.setUserId(res.getId());
       nastavnik.setPrezime(dto.getPrezime());
       nastavnik.setIme(dto.getIme());
       nastavnik.setStatus(dto.getStatus());
       nastavnik.setAktivan(true);
       nastavnik.setEmail(dto.getEmail());
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
       n.setEmail(dto.getEmail());
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
        n.setAktivan(false);
        nastavnikRepository.save(n);
    }
    public List<NastavnikDTO> search(String ime,String prezime,String email){
       List<Nastavnik> n = nastavnikRepository.search(ime,prezime,email);
       return n.stream().map(r->toDTO(r)).toList();
    }

   private static NastavnikDTO toDTO(Nastavnik n){
      NastavnikDTO dto = new NastavnikDTO();
       dto.setId(n.getId());
       dto.setIme(n.getIme());
       dto.setPrezime(n.getPrezime());
       dto.setStatus(n.getStatus());
       dto.setAdresa(n.getAdresa());
       dto.setBiografija(n.getBiografija());
       dto.setEmail(n.getEmail());
       return dto;
   }
}
