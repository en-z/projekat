package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.dto.users.StudentDTO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaIspitaResponseDTO {
    private Long id;
    private int godina;
    private String rok;
    private Date datumPrijave;
    private Long predmetId;
    private StudentDTO student;
}

