package com.projekat.admin_service.service;

import com.projekat.admin_service.DTO.NastavnikDTO;
import com.projekat.admin_service.DTO.StudijskiProgramDTO;
import com.projekat.admin_service.client.NastavnikClient;
import com.projekat.admin_service.entity.StudijskiProgram;
import com.projekat.admin_service.repository.FakultetRepository;
import com.projekat.admin_service.repository.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudijskiProgramService {
    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;
    @Autowired
    private FakultetRepository fakultetRepository;
    @Autowired
    private NastavnikClient nastavnikClient;

    public StudijskiProgramDTO getById(long id){
       StudijskiProgram s= studijskiProgramRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
        StudijskiProgramDTO dto = new StudijskiProgramDTO(s);
        NastavnikDTO nastavnikDTO = nastavnikClient.getNastavnikById(s.getNastavnikId()).getBody();
        dto.setRukovodioc(nastavnikDTO);
        return dto;
    }

    public List<StudijskiProgramDTO> getAktivni() {
        List<StudijskiProgram> programi = studijskiProgramRepository.findByAktivanTrue();
        return programi.stream().map(s -> {
            StudijskiProgramDTO dto = new StudijskiProgramDTO(s);
            try {
                NastavnikDTO rukovodioc = nastavnikClient.getNastavnikById(s.getNastavnikId()).getBody();
                dto.setRukovodioc(rukovodioc);
            } catch (Exception e) {
                dto.setRukovodioc(null);
            }
            return dto;
        }).collect(Collectors.toList());
    }
    public List<StudijskiProgramDTO> getNeaktivne() {
        List<StudijskiProgram> programi = studijskiProgramRepository.findByAktivanFalse();
        return programi.stream().map(s -> {
            StudijskiProgramDTO dto = new StudijskiProgramDTO(s);
            try {
                NastavnikDTO rukovodioc = nastavnikClient.getNastavnikById(s.getNastavnikId()).getBody();
                dto.setRukovodioc(rukovodioc);
            } catch (Exception e) {
                dto.setRukovodioc(null);
            }
            return dto;
        }).collect(Collectors.toList());
    }
    public List<StudijskiProgramDTO> getAll() {
        List<StudijskiProgram> programi = studijskiProgramRepository.findAll();

        return programi.stream().map(s -> {
            StudijskiProgramDTO dto = new StudijskiProgramDTO(s);
            try {
                NastavnikDTO rukovodioc = nastavnikClient.getNastavnikById(s.getNastavnikId()).getBody();
                dto.setRukovodioc(rukovodioc);
            } catch (Exception e) {
                dto.setRukovodioc(null);
            }
            return dto;
        }).collect(Collectors.toList());
    }
    public StudijskiProgramDTO create(StudijskiProgramDTO dto) {
        StudijskiProgram s = new StudijskiProgram();
        s.setNaziv(dto.getNaziv());
        s.setOpis(dto.getOpis());
        s.setFakultet(fakultetRepository.findById(dto.getFakultetId()).orElseThrow(()->new RuntimeException("errro")));
        s.setNastavnikId(dto.getRukovodioc().getId());
        s.setAktivan(true);
        StudijskiProgram saved = studijskiProgramRepository.save(s);

        return dto;
    }
    public StudijskiProgramDTO update(long id, StudijskiProgramDTO dto) {
        StudijskiProgram s = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studijski program not found"));

        s.setNaziv(dto.getNaziv());
        s.setOpis(dto.getOpis());
        s.setFakultet(fakultetRepository.findById(dto.getFakultetId()).orElseThrow(()->new RuntimeException("errro")));
        s.setNastavnikId(dto.getRukovodioc().getId());

        StudijskiProgram updated = studijskiProgramRepository.save(s);

        StudijskiProgramDTO response = new StudijskiProgramDTO(updated);
        return response;
    }
    public void delete(long id) {
        StudijskiProgram  sp = studijskiProgramRepository.findById(id).orElse(null);
        if (sp == null) {
            throw new RuntimeException("Studijski program not found");
        }
        sp.setAktivan(false);
        studijskiProgramRepository.save(sp);
    }
    public List<StudijskiProgramDTO> getByFakultetId(long fakultetId) {
        List<StudijskiProgram> programi = studijskiProgramRepository.findByFakultet_IdAndAktivanTrue(fakultetId);

        return programi.stream().map(s -> {
            StudijskiProgramDTO dto = new StudijskiProgramDTO(s);
            try {
                dto.setRukovodioc(nastavnikClient.getNastavnikById(s.getNastavnikId()).getBody());
            } catch (Exception e) {
                dto.setRukovodioc(null);
            }
            return dto;
        }).collect(Collectors.toList());
    }

}
