package com.projekat.biblioteka_service.service;

import com.projekat.biblioteka_service.client.AuthClient;
import com.projekat.biblioteka_service.client.ImeDTO;
import com.projekat.biblioteka_service.entity.Izdate;
import com.projekat.biblioteka_service.entity.Notifikacija;
import com.projekat.biblioteka_service.repository.IzdateRepository;
import com.projekat.biblioteka_service.repository.NotifikacijeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class Sheduler {
    @Autowired
    private IzdateRepository izdateRepository;
    @Autowired
    private NotifikacijeRepo notifikacijeRepo;
    @Autowired
    private AuthClient authClient;
    @Scheduled(cron = "0 0 0 * * *")
    public void proveriIsteklaZaduzenja(){
        LocalDate today = LocalDate.now();
        List<Izdate> lista = izdateRepository.findByDatumVracanja(today);
        List<Notifikacija> notifikacije = lista.stream().map(iz->makePoruka(iz)).toList();
        notifikacijeRepo.saveAll(notifikacije);
    }
    public Notifikacija makePoruka(Izdate iz){
        Notifikacija n = new Notifikacija(iz);
        ImeDTO ime = authClient.getIme(iz.getUserId()).getBody();
        if(ime!=null){
            String poruka="Korisnik: "+ime.getIme()+" "+ime.getPrezime()+" nije vratio knjigu: "+iz.getKnjiga().getNaziv();
            n.setPoruka(poruka);
        }else{
            n.setPoruka("Korisnik: "+iz.getUserId()+" nije vratio knjigu: "+iz.getKnjiga().getNaziv());
        }
       return n ;
    }
}
