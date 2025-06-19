package com.projekat.nastavnik_service.dto;

import com.projekat.nastavnik_service.entity.Nastavnik;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZvanjeDTO {
    private String naziv;
    private LocalDate datumIzbora;
    private LocalDate datumPrestanka;
    private long nastavnikId;

}
