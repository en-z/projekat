package org.projekat.model;

import lombok.*;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "osoba")
public class Osoba {
    @Id
    private long user_id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    private String ime;
    private String prezime;
    @ManyToOne
    @JoinColumn(name = "adresa_id",referencedColumnName = "id")
    private Adresa adresa;
}
