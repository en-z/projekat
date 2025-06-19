package com.projekat.student_service.dto;

import com.projekat.student_service.entity.IspitniRok;
import com.projekat.student_service.entity.PrijavaIspita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrijavaIspitaDTO {
    private long id;
    private IspitniRok ispitniRok;
    private long predmetId;
    private LocalDate datumOdrzavanja;
    private LocalDate datumPrijave;
    private long studentId;
    public PrijavaIspitaDTO(PrijavaIspita i){
        this.id = i.getId();
        this.ispitniRok = i.getRok();
        this.predmetId = i.getPredmetId();
        this.datumOdrzavanja = i.getDatumOdrzavanja();
        this.datumPrijave = i.getDatumPrijave();
        this.studentId =i.getStudent().getId();
    }
}
