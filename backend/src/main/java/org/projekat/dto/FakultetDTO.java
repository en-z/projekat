package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.dto.users.NastavnikDTO;
import org.projekat.mapper.users.NastavnikMapper;
import org.projekat.model.Adresa;
import org.projekat.model.Fakultet;
import org.projekat.model.Nastavnik;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakultetDTO {
    private Long id;
    private String naziv;
    private String opis;
    private String kontakt;
    private Adresa adresa;
    private NastavnikDTO rektor;
    private UniverzitetDTO univerzitetDTO;

    public FakultetDTO(Fakultet f){
        this.id = f.getId();
        this.naziv=f.getNaziv();
        this.opis=f.getOpis();
        this.kontakt=f.getKontakt();
        this.adresa=f.getAdresa();
        this.rektor = new NastavnikDTO(f.getDekan());
        this.univerzitetDTO = new UniverzitetDTO(f.getUniverzitet());
    }
}
