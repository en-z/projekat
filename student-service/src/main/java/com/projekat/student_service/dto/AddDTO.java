package com.projekat.student_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDTO {
    private String ime;
    private String prezime;
    private String ulica;
    private String broj;
    private String grad;
    private String drzava;
    private long userId;
    private long studiskiId;

}
