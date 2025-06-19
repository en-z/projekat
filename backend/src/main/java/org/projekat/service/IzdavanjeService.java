package org.projekat.service;

import org.projekat.model.Osoba;
import org.projekat.model.Student;
import org.projekat.model.Izdate;
import org.projekat.model.Knjiga;
import org.projekat.repository.IzdateRepository;
import org.projekat.repository.KnjigaRepository;
import org.projekat.repository.users.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IzdavanjeService {
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private OsobaRepository osobaRepository;
    @Autowired
    private IzdateRepository izdateRepository;

    public Izdate izdajKjigu(long kjigaId,long student_id){
        Knjiga k = knjigaRepository.findById(kjigaId).orElseThrow(()->new RuntimeException("Nije pornadjena"));
        if(k.getKolicina()<=0){
            throw new RuntimeException("Kolicina je 0");
        }
        Osoba osoba = osobaRepository.findById(student_id).orElseThrow(()->new RuntimeException("Nije pronadjena osoba"));
        Izdate izdate = new Izdate();
        izdate.setKnjiga(k);
        izdate.setOsoba(osoba);
        izdate.setDatumIzdavanja(LocalDate.now());
        k.setKolicina(k.getKolicina()-1);
        knjigaRepository.save(k);
        return izdateRepository.save(izdate);
    }
    public Izdate vratiKnjigu(long id){
        Izdate i = izdateRepository.findById(id).orElseThrow(()->new RuntimeException("Nema izdatog"));
        i.setDatumVracanja(LocalDate.now());
        Knjiga k = knjigaRepository.findById(i.getKnjiga().getId()).orElseThrow(()->new RuntimeException("Nije pornadjena"));
        k.setKolicina(k.getKolicina()+1);
        knjigaRepository.save(k);
        return izdateRepository.save(i);
    }
    public List<Izdate> getAll(long id){
        return izdateRepository.findByOsoba_User_Id(id);
    }
}
