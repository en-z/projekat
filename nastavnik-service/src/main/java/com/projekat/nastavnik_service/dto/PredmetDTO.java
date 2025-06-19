package com.projekat.nastavnik_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredmetDTO {
    private long id;
    private String naziv;
    private int esbp;
    private int semestar;
    private long studiskiId;
    private int dan;
}
