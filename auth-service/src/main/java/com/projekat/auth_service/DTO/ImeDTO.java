package com.projekat.auth_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImeDTO {
    private long id;
    private String ime;
    private String prezime;
    public ImeDTO(String ime,String prezime){
        this.ime = ime;
        this.prezime = prezime;
    }
}
