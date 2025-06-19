package com.projekat.nastavnik_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    long id;
    String email;
    String password;
    String ime;
    String prezime;
    List<String> roles;
}
