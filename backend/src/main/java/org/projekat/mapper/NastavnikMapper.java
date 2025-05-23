package org.projekat.mapper;

import org.projekat.dto.AngazovanjeDTO;
import org.projekat.dto.PredmetDTO;
import org.projekat.dto.NastavnikDTO;
import org.projekat.model.Nastavnik;
import org.projekat.model.Predmet;

import java.util.stream.Collectors;

public class NastavnikMapper {

    public static NastavnikDTO toDTO(Nastavnik nastavnik) {
        NastavnikDTO dto = new NastavnikDTO();
        dto.setOsobaId(nastavnik.getOsoba().getUser().getId());
        dto.setStatus(nastavnik.getStatus());

        if (nastavnik.getAngazovanja() != null) {
            dto.setAngazovanja(nastavnik.getAngazovanja().stream().map(a -> {
                AngazovanjeDTO aDto = new AngazovanjeDTO();
                aDto.setId(a.getId());
                aDto.setUloga(a.getUloga());

                Predmet p = a.getPredmet();
                if (p != null) {
                    PredmetDTO pDto = new PredmetDTO();
                    pDto.setId(p.getId());
                    pDto.setNaziv(p.getNaziv());
                    pDto.setEsbp(p.getEsbp());
                    aDto.setPredmet(pDto);
                }

                return aDto;
            }).collect(Collectors.toList()));
        }

        return dto;
    }

    public static Nastavnik fromDTO(NastavnikDTO dto) {
        Nastavnik nastavnik = new Nastavnik();
        nastavnik.getOsoba().getUser().setId(dto.getOsobaId());
        nastavnik.setStatus(dto.getStatus());
        return nastavnik;
    }
}
