package com.projekat.admin_service.DTO;

import com.projekat.admin_service.entity.Adresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNastavnikDTO {
    public String ime;
    public String prezime;
    public String email;
    public String password;
    public String biografija;
    public String status;
    public Adresa adresa;
    public List<String> roles;

}
