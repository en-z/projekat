package com.projekat.nastavnik_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IshodIspitaDTO {
    private Long id;
    private Integer brojPokusaja;
    private Long rokId;
    private List<Long> kolokvijumiId;
    private Boolean polozen;
    private Float bodovi;
    private Integer ocena;
    private LocalDateTime datumUnosa;
    private Long studentId;
    private Long predmetId;
    private Long nastavnikId;
    private Long instumentId;
}
