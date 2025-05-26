package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.projekat.model.users.Nastavnik;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studijskiProgram")
public class StudijskiProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String opis;
    @ManyToOne
    @JoinColumn(name = "fakultet_id",referencedColumnName = "id")
    private Fakultet fakultet;
    @OneToOne
    @JoinColumn(name = "rukovodilac",referencedColumnName = "id")
    private Nastavnik rukovodilac;
}
