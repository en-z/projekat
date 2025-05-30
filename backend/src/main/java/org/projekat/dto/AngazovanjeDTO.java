package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AngazovanjeDTO {
    private Long id;
    private PredmetDTO predmet;
    private String uloga;
}
