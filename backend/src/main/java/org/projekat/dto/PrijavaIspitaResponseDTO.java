package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaIspitaResponseDTO {
    private Long id;
    private int godina;
    private int rok;
    private Date datumPrijave;
    private Long predmetId;
    private StudentDTO student;
}

