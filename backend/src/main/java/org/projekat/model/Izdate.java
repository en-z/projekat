package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Osoba;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Izdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "osoba_id",referencedColumnName = "user_id")
    private Osoba osoba;
    @ManyToOne
    @JoinColumn(name = "knjiga_id",referencedColumnName = "id")
    private Knjiga knjiga;
    private LocalDate datumIzdavanja;
    private LocalDate datumVracanja;
}
