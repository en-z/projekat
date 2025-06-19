package com.projekat.nastavnik_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentEvaluacijeDTO {
    private Long id;
    private String tip;
    private String opis;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime datumOdrzavanja;
    private Long predmetId;
    private Long nastavnikId;

}
