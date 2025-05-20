package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObavestenjeDTO {
    private Long id;
    private String naslov;
    private String tekst;
    private LocalDateTime datumPostavljanja;
    private Long predmetId;
    private Long nastavnikId;
}
