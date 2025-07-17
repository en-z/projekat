package com.example.service.osoblje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OsobaRegDto {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String password;
    private List<String> roles;
    private Long fakultetId;
}
