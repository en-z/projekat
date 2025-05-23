package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.projekat.model.Adresa;
import org.projekat.model.Osoba;
import org.projekat.model.User;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private List<String> userType;
    private String email;
    private String password;
    private String ime;
    private String prezime;
    private String ulica;
    private String broj;
    private String grad;
    private String drzava;
    private String status;
    private List<String> roles;

    public Osoba adminFromDTO(AdminDTO dto){
        Osoba o = new Osoba();
        o.setIme(dto.getIme());
        o.setPrezime(dto.getPrezime());
        Adresa a = new Adresa();
        a.setUlica(dto.getUlica());
        a.setGrad(dto.getGrad());
        a.setBroj(dto.getBroj());
        a.setDrzava(dto.getDrzava());
        o.setAdresa(a);
        User u = new User();
        u.setEmail(dto.getEmail());
        u.setRoles(dto.getRoles());
        u.setPassword(dto.getPassword());
        o.setUser(u);
        return o;
    }
}
