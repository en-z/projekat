package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "nastavnik")
public class Nastavnik {
    @Id
    @Column(name = "osoba_id")
    private long id;
    @OneToOne
    @JoinColumn(name = "osoba_id",referencedColumnName = "user_id")
    private Osoba osoba;
    private String status;
    private String biografija;
    @OneToMany
    @JsonManagedReference
    private List<Angazovanje> angazovanja;
}
