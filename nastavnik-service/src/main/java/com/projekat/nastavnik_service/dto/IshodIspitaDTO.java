package com.projekat.nastavnik_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IshodIspitaDTO {
    private Long id;
    private int brojPokusaja;
    private float bodovi;
    private LocalDateTime datumUnosa;
    private Long studentId;
    private Long predmetId;
    private Long nastavnikId;
    private long instumentId;

}
