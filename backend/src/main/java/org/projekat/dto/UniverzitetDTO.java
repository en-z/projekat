package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.dto.users.NastavnikDTO;
import org.projekat.mapper.users.NastavnikMapper;
import org.projekat.model.Adresa;
import org.projekat.model.Nastavnik;
import org.projekat.model.Univerzitet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniverzitetDTO {
    public long id;
    public String naziv;
    public String kontakt;
    public String email;
    public String opis;
    public NastavnikDTO rektor;
    public Adresa adresa;

    public UniverzitetDTO (Univerzitet u ){
        this.id = u.getId();
        this.naziv =u.getNaziv();
        this.kontakt = u.getKontakt();
        this.email = u.getEmail();
        this.opis = u.getOpis();
        this.rektor= NastavnikMapper.toDTO(u.getRektor());
        this.adresa = u.getAdresa();
    }
}

