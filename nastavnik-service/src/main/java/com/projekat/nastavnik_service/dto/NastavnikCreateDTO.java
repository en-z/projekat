package com.projekat.nastavnik_service.dto;

import com.projekat.nastavnik_service.entity.Adresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NastavnikCreateDTO {
    private String biografija;
    private String ime;
    private String prezime;
    private String email;
    private String password;
    private List<String> roles;
    private Adresa adresa;
    private String status;
}
