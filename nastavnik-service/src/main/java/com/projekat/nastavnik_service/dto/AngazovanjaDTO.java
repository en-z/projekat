package com.projekat.nastavnik_service.dto;

import com.projekat.nastavnik_service.entity.Angazovanja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AngazovanjaDTO {
    private long id;
    private long predmetId;
    private long nastavnikId;
    private String uloga;
    public AngazovanjaDTO(Angazovanja a){
        this.predmetId = a.getPredmetId();
        this.nastavnikId = a.getNastavnik().getId();
        this.uloga = a.getUloga();
        this.id = a.getId();
    }
}
