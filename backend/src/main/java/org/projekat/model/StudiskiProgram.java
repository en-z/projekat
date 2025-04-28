package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studiskiProgram")
public class StudiskiProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String opis;
    @ManyToOne
    @JoinColumn(name = "fakultet_id",referencedColumnName = "id")
    private Fakultet fakultet;
    @OneToOne
    @JoinColumn(name = "rukovodioc",referencedColumnName = "osoba_id")
    private Nastavnik rukovodioc;
}
