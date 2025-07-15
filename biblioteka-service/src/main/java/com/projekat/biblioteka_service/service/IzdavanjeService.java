package com.projekat.biblioteka_service.service;

import com.projekat.biblioteka_service.DTO.IzdajDto;
import com.projekat.biblioteka_service.entity.Izdate;
import com.projekat.biblioteka_service.entity.Knjiga;
import com.projekat.biblioteka_service.repository.IzdateRepository;
import com.projekat.biblioteka_service.repository.KnjigaRepository;
import com.projekat.biblioteka_service.repository.NotifikacijeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IzdavanjeService {
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private IzdateRepository izdateRepository;
    @Autowired
    private NotifikacijeRepo notifikacijeRepo;
    public Izdate izdajKjigu(IzdajDto dto){
        Knjiga k = knjigaRepository.findById(dto.getKnjigaId()).orElseThrow(()->new RuntimeException("Nije pornadjena"));
        if(k.getKolicina()<=0){
            throw new RuntimeException("Kolicina je 0");
        }
        Izdate izdate = new Izdate();
        izdate.setKnjiga(k);
        izdate.setUserId(dto.getUserId());
        izdate.setDatumIzdavanja(LocalDate.now());
        izdate.setTrajna(dto.getTrajan());
        izdate.setDatumVracanja(dto.getDatumVracanja());
        k.setKolicina(k.getKolicina()-1);
        knjigaRepository.save(k);
        return izdateRepository.save(izdate);
    }

    public void vratiKnjigu(long izdateId){
        Izdate i =izdateRepository.findById(izdateId).orElseThrow(()->new RuntimeException("Nema ove sa id "));
        Knjiga k = i.getKnjiga();
        k.setKolicina(k.getKolicina()+1);
        knjigaRepository.save(k);
        notifikacijeRepo.deleteByIzdateId(i.getId());
        izdateRepository.delete(i);
    }

    public List<Izdate> getAll(){
        return izdateRepository.findAll();
    }

    public List<Izdate> getAllUser(long id){
        return izdateRepository.findAllByUserId(id);
    }

    public HttpStatus put(long id, LocalDate date){
       Izdate i =izdateRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
       i.setDatumVracanja(date);
       izdateRepository.save(i);
       return HttpStatus.OK;
    }
}
