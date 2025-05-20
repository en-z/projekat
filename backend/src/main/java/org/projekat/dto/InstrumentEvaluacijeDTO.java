package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentEvaluacijeDTO {
    private Long id;
    private String tip;
    private String opis;
    private Long predmetId;
    private Long nastavnikId;
}
