package com.projekat.auth_service.DTO;

import lombok.AllArgsConstructor;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private long id;
    private String email;
    private String password;
    private String ime;
    private String prezime;
    private List<String> roles;
}
