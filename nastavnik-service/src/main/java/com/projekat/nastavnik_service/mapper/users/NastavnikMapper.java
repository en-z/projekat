package com.projekat.nastavnik_service.mapper.users;

import com.projekat.nastavnik_service.dto.AngazovanjaDTO;
import com.projekat.nastavnik_service.dto.NastavnikDTO;
import com.projekat.nastavnik_service.entity.Nastavnik;

import java.util.stream.Collectors;

public class NastavnikMapper {
    public static NastavnikDTO toDTO(Nastavnik nastavnik) {
        NastavnikDTO dto = new NastavnikDTO();
        dto.setId(nastavnik.getId());
        dto.setStatus(nastavnik.getStatus());
        dto.setIme(nastavnik.getIme());
        dto.setPrezime(nastavnik.getPrezime());
        dto.setBiografija(nastavnik.getBiografija());
        if (nastavnik.getAngazovanja() != null) {
            dto.setAngazovanja(nastavnik.getAngazovanja().stream().map(a -> {
                AngazovanjaDTO aDto = new AngazovanjaDTO();
                aDto.setId(a.getId());
                aDto.setUloga(a.getUloga());

                return aDto;
            }).collect(Collectors.toList()));
        }

        return dto;
    }

    public static Nastavnik fromDTO(NastavnikDTO dto) {
        Nastavnik nastavnik = new Nastavnik();
        nastavnik.setId(dto.getId());
        nastavnik.setStatus(dto.getStatus());
        return nastavnik;
    }
}
