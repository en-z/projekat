package com.projekat.nastavnik_service.mapper;

import com.projekat.nastavnik_service.dto.IshodIspitaDTO;
import com.projekat.nastavnik_service.entity.IshodIspita;
import com.projekat.nastavnik_service.entity.Nastavnik;

public class IshodIspitaMapper {

    public static IshodIspitaDTO toDTO(IshodIspita ishod) {
        return new IshodIspitaDTO(
                ishod.getId(),
                ishod.getBrojPokusaja(),
                ishod.getRokId(),
                ishod.getKolokvijumiId(),
                ishod.getPolozen(),
                ishod.getBodovi(),
                ishod.getOcena(),
                ishod.getDatumUnosa(),
                ishod.getStudentId(),
                ishod.getPredmetId(),
                ishod.getNastavnik().getId(),
                ishod.getInstrumentEvaluacije().getId()
        );
    }

    public static IshodIspita fromDTO(
            IshodIspitaDTO dto,
            Nastavnik nastavnik
    ) {
        IshodIspita ishod = new IshodIspita();
        ishod.setRokId(dto.getRokId());
        ishod.setOcena(dto.getOcena());
        ishod.setPolozen(dto.getPolozen());
        ishod.setKolokvijumiId(dto.getKolokvijumiId());
        ishod.setBrojPokusaja(dto.getBrojPokusaja());
        ishod.setBodovi(dto.getBodovi());
        ishod.setStudentId(dto.getStudentId());
        ishod.setPredmetId(dto.getPredmetId());
        ishod.setNastavnik(nastavnik);
        return ishod;
    }
}
