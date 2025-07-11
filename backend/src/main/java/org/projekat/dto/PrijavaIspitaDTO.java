package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.IspitniRok;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaIspitaDTO {
    private Long id;
    private LocalDate datumOdrzavanja;
    private Long rok;
    private Long predmetId;
    private LocalDate datumPrijave;
}
