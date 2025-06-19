package com.projekat.nastavnik_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObavestenjeDTO {
    private Long id;
    private String naslov;
    private String tekst;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime datumPostavljanja;
    private Long predmetId;
    private Long nastavnikId;
    private String ime;
    private String prezime;
}
