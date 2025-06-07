package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.dto.users.StudentDTO;
import org.projekat.model.IspitniRok;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaIspitaResponseDTO {
    private Long id;
    private IspitniRok rok;
    private LocalDate datumPrijave;
    private LocalDate datumOdrzavanja;
    private Long predmetId;
    private StudentDTO student;
}

