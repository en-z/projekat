package com.projekat.student_service.service;

import com.projekat.student_service.client.AdminClient;
import com.projekat.student_service.dto.PredmetDTO;
import com.projekat.student_service.dto.PrijavaIspitaDTO;
import com.projekat.student_service.entity.PrijavaIspita;
import com.projekat.student_service.entity.SlusanjePredmeta;
import com.projekat.student_service.entity.Student;
import com.projekat.student_service.repository.PrijavaIspitaRepository;
import com.projekat.student_service.repository.SlusanjePredmetaRepository;
import com.projekat.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrijavaIspitaService {
    @Autowired
    private PrijavaIspitaRepository prijavaIspitaRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SlusanjePredmetaRepository slusanjePredmetaRepository;
    @Autowired
    private AdminClient adminClient;
    public PrijavaIspitaDTO getById(long id){
       PrijavaIspita p =prijavaIspitaRepository.findById(id).orElseThrow(()->new RuntimeException("nema id "));
        PrijavaIspitaDTO dto = new PrijavaIspitaDTO(p);
        return dto;
    }
    public List<PrijavaIspitaDTO> getAll(){
        return prijavaIspitaRepository.findAll().stream().map(f->new PrijavaIspitaDTO(f)).collect(Collectors.toList());
    }
    public PrijavaIspitaDTO create(PrijavaIspitaDTO prijavaIspitaDTO){
       PrijavaIspita i = new PrijavaIspita();
       i.setRok(prijavaIspitaDTO.getIspitniRok());
       i.setDatumPrijave(prijavaIspitaDTO.getDatumPrijave());
       i.setDatumOdrzavanja(prijavaIspitaDTO.getDatumOdrzavanja());
       i.setPredmetId(prijavaIspitaDTO.getPredmetId());
       Student s =studentRepository.findById(prijavaIspitaDTO.getStudentId()).orElseThrow(()->new RuntimeException("newma id "));
       i.setStudent(s);
       prijavaIspitaRepository.save(i);
       return prijavaIspitaDTO;
    }
    public PrijavaIspitaDTO put(PrijavaIspitaDTO prijavaIspitaDTO){
        PrijavaIspita i =prijavaIspitaRepository.findById(prijavaIspitaDTO.getId()).orElseThrow(()->new RuntimeException("newma id "));
        i.setRok(prijavaIspitaDTO.getIspitniRok());
        i.setDatumPrijave(prijavaIspitaDTO.getDatumPrijave());
        i.setDatumOdrzavanja(prijavaIspitaDTO.getDatumOdrzavanja());
        i.setPredmetId(prijavaIspitaDTO.getPredmetId());
        Student s =studentRepository.findById(prijavaIspitaDTO.getStudentId()).orElseThrow(()->new RuntimeException("newma id "));
        i.setStudent(s);
        prijavaIspitaRepository.save(i);
        return prijavaIspitaDTO;
    }
    public void delete(long id){
        prijavaIspitaRepository.deleteById(id);
    }
    public List<PredmetDTO>getPredmete(long userId){
        Student i = studentRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("No Student"));
        List<SlusanjePredmeta> lista = slusanjePredmetaRepository.findAllByStudentId(i.getId());
        List<Long>ids = new ArrayList<>();
        for(SlusanjePredmeta s: lista){
            ids.add(s.getPredmetId());
        }
        return adminClient.getPredmetiByIds(ids).getBody();
    }
}
