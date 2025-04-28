package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nastavnik")
public class Nastavnik {
    @Id
    private long osoba_id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "osoba_id",referencedColumnName = "user_id")
    private Osoba osoba;
    private String status;
}
