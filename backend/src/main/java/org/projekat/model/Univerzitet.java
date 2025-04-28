package org.projekat.model;

import lombok.*;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "univerzite")
public class Univerzitet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String kontakt;
    private String opis;
    //private Nastavnik rektor;
    @OneToOne
    @JoinColumn(name = "adresa_id",referencedColumnName = "id")
    private Adresa adresa;
}
