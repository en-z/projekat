package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.users.Nastavnik;

@Entity
@Table(name = "angazovanje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Angazovanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id")
    @JsonBackReference("nastavnik-angazovanja")
    private Nastavnik nastavnik;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    private String uloga;
}
