package com.example.service.osoblje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String password;
    private List<String> roles;
    public RegisterDTO(OsobaRegDto dto){
        this.ime = dto.getIme();
        this.prezime= dto.getPrezime();
        this.email= dto.getEmail();
        this.password = dto.getPassword();
        this.roles = new ArrayList<>();
        this.roles.add("ROLE_OSOBLJE");
    }
}
