package org.projekat.mapper.users;

import org.projekat.dto.AngazovanjeDTO;
import org.projekat.dto.PredmetDTO;
import org.projekat.dto.users.NastavnikDTO;
import org.projekat.model.Predmet;
import org.projekat.model.users.Nastavnik;

import java.util.stream.Collectors;

public class NastavnikMapper {

    public static NastavnikDTO toDTO(Nastavnik nastavnik) {
        NastavnikDTO dto = new NastavnikDTO();
        dto.setOsobaId(nastavnik.getId());
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
                    pDto.setEspb(p.getEspb());
                    aDto.setPredmet(pDto);
                }

                return aDto;
            }).collect(Collectors.toList()));
        }

        return dto;
    }

    public static Nastavnik fromDTO(NastavnikDTO dto) {
        Nastavnik nastavnik = new Nastavnik();
        nastavnik.setId(dto.getOsobaId());
        nastavnik.setStatus(dto.getStatus());
        return nastavnik;
    }
}
