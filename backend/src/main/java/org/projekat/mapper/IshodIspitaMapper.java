package org.projekat.mapper;

import org.projekat.dto.IshodIspitaDTO;
import org.projekat.model.IshodIspita;
import org.projekat.model.Nastavnik;
import org.projekat.model.Predmet;
import org.projekat.model.Student;

public class IshodIspitaMapper {

    public static IshodIspitaDTO toDTO(IshodIspita ishod) {
        return new IshodIspitaDTO(
                ishod.getId(),
                ishod.getBodovi(),
                ishod.getBrojPokusaja(),
                ishod.getBodovi(),
                ishod.getDatumUnosa(),
                ishod.getStudent().getOsoba_id(),
                ishod.getPredmet().getId(),
                ishod.getNastavnik().getId(),
                ishod.getInstrumentEvaluacije().getId()
        );
    }

    public static IshodIspita fromDTO(
            IshodIspitaDTO dto,
            Student student,
            Predmet predmet,
            Nastavnik nastavnik
    ) {
        IshodIspita ishod = new IshodIspita();
        ishod.setId(dto.getId());
        ishod.setBrojPokusaja(dto.getBrojPokusaja());
        ishod.setBodovi(dto.getBodovi());
        ishod.setStudent(student);
        ishod.setPredmet(predmet);
        ishod.setNastavnik(nastavnik);
        return ishod;
    }
}
