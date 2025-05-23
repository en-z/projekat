package org.projekat.model;

import lombok.*;

import jakarta.persistence.*;
import org.projekat.service.UniverzitetService;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "univerzitet")
public class Univerzitet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String kontakt;
    private String email;
    private String opis;
    @OneToOne
    @JoinColumn(name = "nastavnik_osoba_rektor",referencedColumnName = "osoba_id")
    private Nastavnik rektor;
    @OneToOne
    @JoinColumn(name = "adresa_id",referencedColumnName = "id")
    private Adresa adresa;
    public void update(Univerzitet u){
        this.naziv = u.getNaziv();
        this.kontakt = u.getKontakt();
        this.email = u.getEmail();
        this.opis = u.getOpis();
        this.rektor = u.getRektor();
        this.adresa = u.getAdresa();
    }
}
