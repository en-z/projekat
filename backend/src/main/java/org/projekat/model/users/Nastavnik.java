package org.projekat.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Angazovanje;

import java.util.List;

@Entity
@Table(name = "nastavnik")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nastavnik {

    @Id
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    @JsonIgnore
    private Osoba osoba;

    private String status;

    @OneToMany(mappedBy = "nastavnik")
    @JsonManagedReference("nastavnik-angazovanja")
    private List<Angazovanje> angazovanja;
}

