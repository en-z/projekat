package com.projekat.nastavnik_service.dto;

import com.projekat.nastavnik_service.entity.Adresa;
import com.projekat.nastavnik_service.entity.Angazovanja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NastavnikDTO {
    private Long id;
    private String biografija;
    private String ime;
    private String prezime;
    private String email;
    private Long userId;
    private Adresa adresa;
    private String status;
    private List<AngazovanjaDTO> Angazovanja;

}
