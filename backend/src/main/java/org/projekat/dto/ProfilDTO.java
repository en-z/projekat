package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Adresa;
import org.projekat.model.Nastavnik;
import org.projekat.model.Osoba;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfilDTO {
        private String email;
        private String password;
        private String ime;
        private String prezime;
        private Adresa adresa;
        private String status;
        private String biografija;

    public ProfilDTO OtoDto(Osoba o){
        ProfilDTO p = new ProfilDTO();
        p.setAdresa(o.getAdresa());
        p.setIme(o.getIme());
        p.setPrezime(o.getPrezime());
        p.setEmail(o.getUser().getEmail());
        p.setPassword("");
        return p;
    }
    public ProfilDTO ntoDto(Nastavnik n){
        ProfilDTO p = OtoDto(n.getOsoba());
        p.setStatus(n.getStatus());
        p.setBiografija(n.getBiografija());
        return p;
    }
}
