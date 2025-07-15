package com.projekat.admin_service.service;

import com.projekat.admin_service.DTO.PredmetDTO;
import com.projekat.admin_service.entity.Predmet;
import com.projekat.admin_service.entity.StudijskiProgram;
import com.projekat.admin_service.repository.PredmetRepository;
import com.projekat.admin_service.repository.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredmetService {
    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;
    @Autowired
    private PredmetRepository predmetRepository;

    public List<PredmetDTO> getAll(){
        return predmetRepository.findAll().stream().map(p -> new PredmetDTO(p) ).collect(Collectors.toList());
    }
    public List<PredmetDTO> getAktivne(){
        return predmetRepository.findByAktivanTrue().stream().map(p->new PredmetDTO(p)).collect(Collectors.toList());
    }
    public List<PredmetDTO> getNeaktivne(){
        return predmetRepository.findByAktivanFalse().stream().map(p->new PredmetDTO(p)).collect(Collectors.toList());
    }
    public PredmetDTO getById(long id){
        Predmet p =predmetRepository.findById(id).orElseThrow(()->new RuntimeException("no id"));
        PredmetDTO dto = new PredmetDTO(p);
        return dto;
    }
    public PredmetDTO create(PredmetDTO dto){
        Predmet p = dto.toPredmet();
        StudijskiProgram sp = studijskiProgramRepository.findById(dto.getStudiskiId()).orElseThrow(()->new RuntimeException("Error"));
        p.setStudijskiProgram(sp);
        p.setAktivan(true);
        predmetRepository.save(p);
        return dto;
    }
    public void delete(long id){
        Predmet p = predmetRepository.findById(id).orElse(null);
        if(p == null){
            throw new RuntimeException("no ID");
        }
        p.setAktivan(false);
        predmetRepository.save(p);
    }
    public List<PredmetDTO>findByFakultetId(long id){
        return predmetRepository.findByStudijskiProgram_IdAndAktivanTrue(id).stream().map(p->new PredmetDTO(p)).collect(Collectors.toList());
    }
    public List<PredmetDTO>findByIDS(List<Long>ids){
       return predmetRepository.findAllById(ids).stream().map(p->new PredmetDTO(p)).toList();
    }
}
