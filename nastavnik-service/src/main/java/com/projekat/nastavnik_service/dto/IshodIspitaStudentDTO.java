package com.projekat.nastavnik_service.dto;

import com.projekat.nastavnik_service.entity.IshodIspita;
import com.projekat.nastavnik_service.mapper.users.NastavnikMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IshodIspitaStudentDTO {
    private long id;
    private int brojPokusaja;
    private float bodovi;
    private PredmetDTO predmet;
    private NastavnikDTO nastavnik;

    public IshodIspitaStudentDTO (IshodIspita a,PredmetDTO dto){
        this.id =a.getId();
        this.nastavnik = NastavnikMapper.toDTO(a.getNastavnik());
        this.bodovi = a.getBodovi();
        this.brojPokusaja= a.getBrojPokusaja();
        this.predmet =dto;
    }
}
