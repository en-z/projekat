package org.projekat.dto;

import lombok.Data;

@Data
public class AngazovanjeDTO {
    private Long id;
    private PredmetDTO predmet;
    private String uloga;
}
