package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IshodIspitaDTO {
    private Long id;
    private int ocena;
    private int brojPokusaja;
    private int bodovi;
    private LocalDateTime datumUnosa;
    private Long studentId;
    private Long predmetId;
    private Long nastavnikId;
    private long instumentId;
}
