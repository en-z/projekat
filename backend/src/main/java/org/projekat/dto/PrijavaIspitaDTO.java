package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaIspitaDTO {
    private Long id;
    private int godina;
    private String rok;
    private Date datumPrijave;
    private Long studentId;
    private Long predmetId;
}
