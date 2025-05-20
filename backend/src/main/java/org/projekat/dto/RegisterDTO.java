package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String email;
    private String password;
    private String passwordConf;
    private String ime;
    private String prezime;
    private String ulica;
    private String broj;
    private String grad;
    private String drzava;
}
