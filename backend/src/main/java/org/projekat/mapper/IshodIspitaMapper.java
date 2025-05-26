package org.projekat.mapper;

import org.projekat.dto.IshodIspitaDTO;
import org.projekat.model.IshodIspita;
import org.projekat.model.Predmet;
import org.projekat.model.users.Nastavnik;
import org.projekat.model.users.Student;

public class IshodIspitaMapper {

    public static IshodIspitaDTO toDTO(IshodIspita ishod) {
        return new IshodIspitaDTO(
                ishod.getId(),
                ishod.getOcena(),
                ishod.getBrojPokusaja(),
                ishod.getBodovi(),
                ishod.isPolozen(),
                ishod.getDatumUnosa(),
                ishod.getStudent().getId(),
                ishod.getPredmet().getId(),
                ishod.getNastavnik().getId()
        );
    }

    public static IshodIspita fromDTO(
            IshodIspitaDTO dto,
            Student student,
            Predmet predmet,
            Nastavnik nastavnik
    ) {
        IshodIspita ishod = new IshodIspita();
        if (dto.getId() != null) {
            ishod.setId(dto.getId());
        }
        ishod.setOcena(dto.getOcena());
        ishod.setBrojPokusaja(dto.getBrojPokusaja());
        ishod.setBodovi(dto.getBodovi());
        ishod.setPolozen(dto.isPolozen());
        ishod.setStudent(student);
        ishod.setPredmet(predmet);
        ishod.setNastavnik(nastavnik);
        return ishod;
    }
}
